require('dotenv').config({ path: '/home/ssafy/linear-dev-log/.env' });
const { LinearClient } = require('@linear/sdk');

async function getSlug() {
  const linearClient = new LinearClient({ apiKey: process.env.LINEAR_API_KEY });
  const organization = await linearClient.organization;
  console.log(organization.urlKey);
}

getSlug().catch(console.error);
