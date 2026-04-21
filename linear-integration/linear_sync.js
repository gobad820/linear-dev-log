const { LinearClient } = require('@linear/sdk');

async function sync() {
  const linearClient = new LinearClient({
    apiKey: process.env.LINEAR_API_KEY
  });

  const me = await linearClient.viewer;
  const teams = await me.teams();
  
  if (teams.nodes.length === 0) {
    console.log("No teams found.");
    return;
  }

  for (const team of teams.nodes) {
    console.log("Processing Team: " + team.name + " (" + team.key + ")");
    
    // 1. Get Cycles
    const cycles = await team.cycles();
    const now = new Date();
    const currentCycle = cycles.nodes.find(c => c.startsAt <= now && c.endsAt >= now);
    
    if (currentCycle) {
      console.log("Current Cycle: " + currentCycle.number + " (Ends: " + currentCycle.endsAt + ")");
      
      const overdueCycles = cycles.nodes.filter(c => c.endsAt < now && c.uncompletedIssuesCount > 0);
      for (const cycle of overdueCycles) {
        console.log("Moving issues from overdue Cycle " + cycle.number + "...");
        const issues = await cycle.issues();
        for (const issue of issues.nodes) {
          if (!issue.completedAt && !issue.canceledAt) {
            console.log(" - Moving Issue: " + issue.title + " (" + issue.identifier + ")");
            await issue.update({ cycleId: currentCycle.id });
          }
        }
      }
    } else {
      console.log("No active cycle found for this team.");
    }

    // 2. Distribute issues to projects
    const projects = await team.projects();
    const issues = await team.issues({ filter: { state: { type: { neq: 'completed' } } } });
    
    for (const issue of issues.nodes) {
      if (issue.projectId) continue;

      let targetProject = null;
      const title = issue.title.toLowerCase();
      
      if (title.includes('db') || title.includes('database')) {
        targetProject = projects.nodes.find(p => p.name.toLowerCase().includes('db') || p.name.toLowerCase().includes('database'));
      } else if (title.includes('backend') || title.includes('백엔드') || title.includes('api')) {
        targetProject = projects.nodes.find(p => p.name.toLowerCase().includes('backend') || p.name.toLowerCase().includes('백엔드'));
      } else if (title.includes('algo') || title.includes('algorithm') || title.includes('알고리즘')) {
        targetProject = projects.nodes.find(p => p.name.toLowerCase().includes('algo') || p.name.toLowerCase().includes('algorithm') || p.name.toLowerCase().includes('알고리즘'));
      }

      if (targetProject) {
        console.log("Assigning Issue: " + issue.title + " -> Project: " + targetProject.name);
        await issue.update({ projectId: targetProject.id });
      }
    }
  }
}

sync().catch(console.error);
