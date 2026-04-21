const { LinearClient } = require('@linear/sdk');

async function check() {
  const linearClient = new LinearClient({
    apiKey: process.env.LINEAR_API_KEY
  });

  const projects = await linearClient.projects();
  for (const p of projects.nodes) {
    console.log("Project: " + p.name + " | Icon: " + p.icon);
  }
}

check().catch(console.error);
