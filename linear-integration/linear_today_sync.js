const { LinearClient } = require('@linear/sdk');

async function syncToday() {
  const linearClient = new LinearClient({
    apiKey: process.env.LINEAR_API_KEY
  });

  const me = await linearClient.viewer;
  const teams = await me.teams();
  const team = teams.nodes[0];
  const projects = await team.projects();
  const javaProject = projects.nodes.find(p => p.name.includes('Java Backend Fundamentals'));

  // 1. Add morning JSP lecture issue
  console.log("Adding JSP Lecture issue...");
  await linearClient.createIssue({
    teamId: team.id,
    projectId: javaProject ? javaProject.id : null,
    title: "[Lecture] JSP Fundamentals - Scriptlets, Directives & EL/JSTL",
    description: "오전 수업 내용: JSP 생명주기, 스크립트 요소, 내장 객체 및 EL/JSTL 기초",
    priority: 1,
    labelIds: [] 
  });

  // 2. Fetch all current issues to clean up priorities for "Today"
  const issues = await linearClient.issues({
    filter: { state: { type: { neq: 'completed' } }, and: [{ state: { type: { neq: 'canceled' } } }] }
  });

  console.log("Finalizing priorities for today's clean view...");
  for (const issue of issues.nodes) {
    const title = issue.title;
    
    // Set Priority 1 (Urgent) for everything identified in the recommended schedule
    if (
      title.includes('JSP') || 
      title.includes('weekly-review') || 
      title.includes('HW') || 
      title.includes('KMP') ||
      title.includes('면접')
    ) {
      await issue.update({ priority: 1 });
    } else {
      // Move background/deep-theory tasks to P2 or P3 to declutter the "Urgent" view
      await issue.update({ priority: 3 });
    }
  }
}

syncToday().catch(console.error);
