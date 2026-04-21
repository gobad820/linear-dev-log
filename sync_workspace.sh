#!/bin/bash

# 색상 설정
GREEN='\039[0;32m'
BLUE='\039[0;34m'
NC='\039[0m' # No Color

echo -e "${BLUE}🔄 Windows Workspace -> WSL Linear Repository 동기화 시작...${NC}"

# Windows 워크스페이스 경로
WIN_WORKSPACE="/mnt/c/SSAFY/1557591/workspace/05.backend"
# WSL 레포지토리 경로
WSL_REPO="/home/ssafy/linear-dev-log/backend"

# 1. 최신 코드 복사 (빌드 결과물인 target, .metadata 등 불필요한 파일 제외)
echo "📦 소스 코드 복사 중..."
rsync -av --exclude 'target' --exclude '.settings' --exclude '.classpath' --exclude '.project' "$WIN_WORKSPACE/score-app" "$WSL_REPO/"
rsync -av --exclude 'target' --exclude '.settings' --exclude '.classpath' --exclude '.project' "$WIN_WORKSPACE/score-mvc-app" "$WSL_REPO/"
rsync -av --exclude 'target' --exclude '.settings' --exclude '.classpath' --exclude '.project' "$WIN_WORKSPACE/score-service-app" "$WSL_REPO/"
rsync -av --exclude 'target' --exclude '.settings' --exclude '.classpath' --exclude '.project' "$WIN_WORKSPACE/BE_03_lab.zip_expanded/BE_03_lab" "$WSL_REPO/"

echo -e "${GREEN}✅ 소스 코드 동기화 완료!${NC}"

# 2. Linear 자동 동기화 스크립트 실행 (선택사항)
echo -e "${BLUE}🤖 Linear 이슈 상태 점검 중...${NC}"
cd /home/ssafy/linear-dev-log
node ./linear-integration/linear_git_sync.js

echo -e "${GREEN}🎉 모든 준비가 끝났습니다! 이제 git add . 후 커밋하세요.${NC}"
