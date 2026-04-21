const { LinearClient } = require('@linear/sdk');

async function updateProject() {
  const linearClient = new LinearClient({
    apiKey: process.env.LINEAR_API_KEY
  });

  const me = await linearClient.viewer;
  const teams = await me.teams();
  const team = teams.nodes[0];

  const projects = await team.projects();
  const studyProject = projects.nodes.find(p => p.name.includes('Java Backend Fundamentals'));

  if (studyProject) {
    console.log("Updating project icon with emoji...");
    await linearClient.updateProject(studyProject.id, {
      icon: "📚"
    });
    console.log("Emoji icon updated successfully.");
  }
}

updateProject().catch(console.error);
