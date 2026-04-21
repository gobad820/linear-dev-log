require('dotenv').config({ path: '/home/ssafy/linear-dev-log/.env' });
const { LinearClient } = require('@linear/sdk');

async function showP1Issues() {
  if (!process.env.LINEAR_API_KEY) {
    console.error("Error: LINEAR_API_KEY is not set in .env file.");
    return;
  }

  const linearClient = new LinearClient({
    apiKey: process.env.LINEAR_API_KEY
  });

  try {
    const issues = await linearClient.issues({
      filter: {
        priority: { eq: 1 },
        state: { type: { neq: 'completed' } },
        and: [{ state: { type: { neq: 'canceled' } } }]
      }
    });

    console.log("🔥 Today's Priority 1 (Urgent) Issues:\n");
    if (issues.nodes.length === 0) {
      console.log("No Priority 1 issues found! You are all caught up.");
      return;
    }

    issues.nodes.forEach(issue => {
      console.log(`- [${issue.identifier}] ${issue.title}`);
    });
    console.log("\n(Tip: Run ./start_task.sh to automatically create a branch for the top issue!)");
  } catch (error) {
    console.error("Failed to fetch issues:", error.message);
  }
}

showP1Issues();
