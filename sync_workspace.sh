#!/bin/bash

# 색상 설정
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m'

# 경로 설정
WIN_WORKSPACE="/mnt/c/SSAFY/1557591/workspace/05.backend"
WSL_REPO="/home/ssafy/linear-dev-log/backend"

# 현재 브랜치명 가져오기
BRANCH_NAME=$(git symbolic-ref --short HEAD 2>/dev/null)

echo -e "${BLUE}🔄 스마트 동기화 시작 (현재 브랜치: $BRANCH_NAME)${NC}"

# 브랜치에 따른 동기화 대상 설정
PROJECT_DIR=""
case "$BRANCH_NAME" in
    *SSA-56*) PROJECT_DIR="score-app" ;;
    *SSA-57*) PROJECT_DIR="score-mvc-app" ;;
    *SSA-58*) PROJECT_DIR="score-service-app" ;;
    *SSA-59*) PROJECT_DIR="temp-app" ;;
    *SSA-50*|*SSA-51*|*SSA-52*|*SSA-54*) PROJECT_DIR="BE_03_lab.zip_expanded/BE_03_lab" ;;
    *) 
        echo -e "${YELLOW}⚠️ 매칭되는 프로젝트를 찾지 못했습니다. 전체 프로젝트를 동기화합니다.${NC}"
        PROJECT_DIR="ALL"
        ;;
esac

sync_project() {
    local folder=$1
    echo -e "📦 [${BLUE}$folder${NC}] 소스 코드 가져오는 중..."
    rsync -av --delete --exclude 'target' --exclude '.settings' --exclude '.classpath' --exclude '.project' --exclude '.metadata' \
        "$WIN_WORKSPACE/$folder/" "$WSL_REPO/${folder##*/}/"
}

if [ "$PROJECT_DIR" == "ALL" ]; then
    sync_project "score-app"
    sync_project "score-mvc-app"
    sync_project "score-service-app"
    sync_project "temp-app"
    sync_project "BE_03_lab.zip_expanded/BE_03_lab"
else
    sync_project "$PROJECT_DIR"
fi

echo -e "${GREEN}✅ 동기화 완료! 이제 안심하고 'git add .' 하셔도 됩니다.${NC}"
