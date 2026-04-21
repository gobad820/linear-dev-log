const { LinearClient } = require('@linear/sdk');

async function prioritize() {
  const linearClient = new LinearClient({
    apiKey: process.env.LINEAR_API_KEY
  });

  const issues = await linearClient.issues({
    filter: { state: { type: { neq: 'completed' } }, and: [{ state: { type: { neq: 'canceled' } } }] }
  });

  for (const issue of issues.nodes) {
    const title = issue.title;
    let newPriority = 3; // Default: Normal

    // Urgent (Priority 1): Weekly Reviews and HW that tie into current learning
    if (title.includes('weekly-review') || title.includes('HW') || title.includes('면접')) {
      newPriority = 1; 
    }
    // High (Priority 2): Core concept deep dives (MVCC, Lock, Transaction)
    else if (title.includes('MVCC') || title.includes('잠금') || title.includes('격리') || title.includes('트랜잭션')) {
      newPriority = 2;
    }
    // Normal (Priority 3): General review and others
    else {
      newPriority = 3;
    }

    if (issue.priority !== newPriority) {
      console.log(`Updating Priority: [${issue.identifier}] ${title} -> ${newPriority}`);
      await issue.update({ priority: newPriority });
    }
  }
}

prioritize().catch(console.error);
