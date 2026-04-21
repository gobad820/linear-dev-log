const { LinearClient } = require('@linear/sdk');
const { execSync } = require('child_process');

async function syncWithGit() {
  const linearClient = new LinearClient({
    apiKey: process.env.LINEAR_API_KEY
  });

  try {
    // Get current branch name
    const branchName = execSync('git rev-parse --abbrev-ref HEAD').toString().trim();
    console.log(`Current Branch: ${branchName}`);

    // Extract issue key (e.g., SSA-50) from branch name
    const match = branchName.match(/[A-Z]+-\d+/);
    if (!match) {
      console.log("No Linear issue key found in branch name.");
      return;
    }

    const issueKey = match[0];
    console.log(`Found Issue Key: ${issueKey}`);

    const issue = await linearClient.issue(issueKey);
    if (issue) {
      // Update status to 'In Progress' (This ID depends on your workspace, usually 2nd or 3rd state)
      // For now, let's just log and you can request specific state updates.
      console.log(`Issue "${issue.title}" is now synced with your local development.`);
      
      // Example: Set priority to High because you are working on it
      await issue.update({ priority: 1 });
      console.log("Priority updated to 1 (Urgent) as it's currently being developed.");
    }
  } catch (e) {
    console.error("Error: Make sure you are in a git repository.");
  }
}

syncWithGit().catch(console.error);
