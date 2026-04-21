const { LinearClient } = require('@linear/sdk');

async function organize() {
  const linearClient = new LinearClient({
    apiKey: process.env.LINEAR_API_KEY
  });

  const me = await linearClient.viewer;
  const teams = await me.teams();
  const team = teams.nodes[0];

  if (!team) {
    console.log("No team found.");
    return;
  }

  console.log("Team: " + team.name);

  // 1. Ensure "Java Backend Fundamentals" project exists
  const projects = await team.projects();
  let studyProject = projects.nodes.find(p => p.name.includes('Java Backend Fundamentals'));

  if (!studyProject) {
    console.log("Creating new project: Java Backend Fundamentals");
    const projectPayload = await linearClient.createProject({
      name: "Java Backend Fundamentals",
      teamIds: [team.id],
      description: "Servlet, FilterChain, SpringMVC 등 백엔드 핵심 개념 학습"
    });
    studyProject = await projectPayload.project;
  }

  // 2. Find issues and assign to project
  const issues = await team.issues();
  const keywords = ['servlet', 'filterchain', 'springmvc', 'filter chain', 'mvc'];

  for (const issue of issues.nodes) {
    const title = issue.title.toLowerCase();
    const description = (issue.description || "").toLowerCase();
    
    const matches = keywords.some(k => title.includes(k) || description.includes(k));
    
    if (matches) {
      console.log("Moving Issue: " + issue.title + " -> Project: " + studyProject.name);
      await issue.update({ projectId: studyProject.id });
    }
  }
}

organize().catch(console.error);
