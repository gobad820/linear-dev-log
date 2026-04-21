require('dotenv').config({ path: '/home/ssafy/linear-dev-log/.env' });
const { LinearClient } = require('@linear/sdk');
const prompts = require('prompts');
const { execSync } = require('child_process');

async function startTask() {
  if (!process.env.LINEAR_API_KEY) {
    console.error("Error: LINEAR_API_KEY is not set in .env file.");
    return;
  }

  const linearClient = new LinearClient({
    apiKey: process.env.LINEAR_API_KEY
  });

  try {
    console.log("⏳ Linear에서 오늘 할 일(Priority 1)을 불러오는 중...");
    
    const issues = await linearClient.issues({
      filter: {
        priority: { eq: 1 },
        state: { type: { neq: 'completed' } },
        and: [{ state: { type: { neq: 'canceled' } } }]
      }
    });

    if (issues.nodes.length === 0) {
      console.log("🎉 완벽합니다! 오늘 계획된 긴급(Priority 1) 이슈가 모두 끝났습니다.");
      return;
    }

    // 1. 프롬프트 메뉴용 리스트 생성
    const choices = issues.nodes.map(issue => ({
      title: `[${issue.identifier}] ${issue.title}`,
      value: {
        id: issue.identifier,
        // 브랜치 이름에 쓸 수 있게 영문자 소문자로 치환하고 특수문자는 대시로 변경 (예: score-app-init)
        slug: issue.title.toLowerCase().replace(/[^a-z0-9]+/g, '-').replace(/(^-|-$)+/g, '')
      }
    }));

    // 2. 터미널 UI 출력 및 방향키 선택 받기
    const response = await prompts({
      type: 'autocomplete',
      name: 'selectedIssue',
      message: '🚀 지금 바로 시작할 작업을 방향키로 선택하세요:',
      choices: choices,
      limit: 10
    });

    if (!response.selectedIssue) {
      console.log("작업 선택이 취소되었습니다.");
      return;
    }

    // 3. 브랜치 이름 생성 (예: SSA-56/score-app-basic)
    // 한글 등 영문 외 문자가 많아서 slug가 비어버리면 그냥 id만 사용
    const slug = response.selectedIssue.slug ? response.selectedIssue.slug.substring(0, 30) : 'dev';
    const branchName = `${response.selectedIssue.id}/${slug}`;

    console.log(`\n🔧 선택한 이슈: [${response.selectedIssue.id}]`);
    console.log(`🌿 브랜치 생성 및 이동: git checkout -b ${branchName}`);

    // 4. Git 명령어 실행
    try {
      execSync(`git checkout -b ${branchName}`, { stdio: 'inherit' });
      console.log(`\n✅ 성공적으로 '${branchName}' 브랜치로 이동했습니다!`);
      console.log(`이제 코딩을 시작하시고, 작업이 끝나면 ./sync_workspace.sh 로 동기화하세요.`);
    } catch (gitError) {
      console.error("\n❌ Git 브랜치 생성 실패. 이미 존재하는 브랜치인지 확인하세요.");
    }

  } catch (error) {
    console.error("Failed to fetch issues:", error.message);
  }
}

startTask();
