const { LinearClient } = require('@linear/sdk');

async function prioritize() {
  const linearClient = new LinearClient({
    apiKey: process.env.LINEAR_API_KEY
  });

  const me = await linearClient.viewer;
  const issues = await linearClient.issues({
    filter: {
      state: { type: { neq: 'completed' } },
      and: [
        { state: { type: { neq: 'canceled' } } }
      ]
    }
  });

  console.log("Current Issues for Analysis:");
  for (const issue of issues.nodes) {
    console.log(`- [${issue.identifier}] ${issue.title} (Priority: ${issue.priority})`);
  }
}

prioritize().catch(console.error);
