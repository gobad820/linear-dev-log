const { LinearClient } = require('@linear/sdk');

async function reorganize() {
  const linearClient = new LinearClient({
    apiKey: process.env.LINEAR_API_KEY
  });

  const me = await linearClient.viewer;
  const teams = await me.teams();
  const team = teams.nodes[0];
  const projects = await team.projects();

  const projectMap = {
    db: projects.nodes.find(p => p.name.toLowerCase().includes('database')),
    algo: projects.nodes.find(p => p.name.toLowerCase().includes('algorithm')),
    java: projects.nodes.find(p => p.name.includes('Java Backend Fundamentals')),
    backend: projects.nodes.find(p => p.name === 'Backend (백엔드)'),
    cs: projects.nodes.find(p => p.name.includes('CS')),
    opic: projects.nodes.find(p => p.name.includes('OPIc'))
  };

  const issues = await team.issues();

  for (const issue of issues.nodes) {
    if (issue.completedAt || issue.canceledAt) continue;

    const title = issue.title.toLowerCase();
    const desc = (issue.description || "").toLowerCase();
    const content = title + " " + desc;

    let targetProject = null;

    // 1. Database Priority (Transactions, Logs, Locks, MVCC, SQL, DB engines)
    if (content.match(/db|database|transaction|acid|log|pool|lock|mvcc|isolation|sql|postgresql|mysql|쿼리|트랜잭션|격리/)) {
      targetProject = projectMap.db;
    } 
    // 2. Algorithm Priority
    else if (content.match(/algo|algorithm|코테|코딩|특강|백준|프로그래머스|알고리즘/)) {
      targetProject = projectMap.algo;
    }
    // 3. Java Backend Fundamentals (Servlet, JSP, Spring MVC, Filter, Dispatcher)
    else if (content.match(/servlet|jsp|filter|mvc|dispatcher|spring mvc|forward|redirect|lifecycle/)) {
      targetProject = projectMap.java;
    }
    // 4. OPIc
    else if (content.match(/opic|오픽|영어/)) {
      targetProject = projectMap.opic;
    }
    // 5. CS
    else if (content.match(/cs|컴퓨터과학|os|network|operating system/)) {
      targetProject = projectMap.cs;
    }

    if (targetProject && issue.projectId !== targetProject.id) {
      console.log(`Reassigning: "${issue.title}" -> ${targetProject.name}`);
      await issue.update({ projectId: targetProject.id });
    }
  }
}

reorganize().catch(console.error);
