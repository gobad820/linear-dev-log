const { LinearClient } = require('@linear/sdk');

async function addTasks() {
  const linearClient = new LinearClient({
    apiKey: process.env.LINEAR_API_KEY
  });

  const me = await linearClient.viewer;
  const teams = await me.teams();
  const team = teams.nodes[0];
  const projects = await team.projects();
  const javaProject = projects.nodes.find(p => p.name.includes('Java Backend Fundamentals'));

  const tasks = [
    { title: "[LAB] Score App - Basic Servlet Implementation", desc: "score-app: 기초적인 서블릿 기반 성적 관리 시스템 구현" },
    { title: "[LAB] Score MVC App - Controller & JSP Separation", desc: "score-mvc-app: MVC 패턴을 적용한 성적 관리 시스템 리팩토링" },
    { title: "[LAB] Score Service App - Layered Architecture (Service/DAO)", desc: "score-service-app: 서비스와 DAO 계층을 분리한 고급 성적 관리 아키텍처 구현" }
  ];

  console.log("Adding Score Management tasks to Linear...");
  for (const task of tasks) {
    const issue = await linearClient.createIssue({
      teamId: team.id,
      projectId: javaProject ? javaProject.id : null,
      title: task.title,
      description: task.desc,
      priority: 1 // Urgent for today's practice
    });
    const issueData = await issue.issue;
    console.log(`Created: [${issueData.identifier}] ${issueData.title}`);
  }
}

addTasks().catch(console.error);
