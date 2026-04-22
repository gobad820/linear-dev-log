#!/bin/bash
# 작업을 마치고 로컬 환경을 main으로 깔끔하게 정리하는 스크립트

CURRENT_BRANCH=$(git symbolic-ref --short HEAD)

if [ "$CURRENT_BRANCH" == "main" ]; then
    echo "현재 main 브랜치입니다. 정리할 작업 브랜치로 이동 후 실행하거나 'git branch -d'를 사용하세요."
else
    echo "🧹 '$CURRENT_BRANCH' 작업을 정리하고 main으로 복귀합니다..."
    
    # 1. main으로 이동
    git checkout main
    
    # 2. 원격 최신 내용 가져오기
    git pull origin main
    
    # 3. 작업했던 로컬 브랜치 삭제 (머지된 경우에만 안전하게 삭제)
    git branch -d "$CURRENT_BRANCH"
    
    # 4. 원격에 이미 지워진 브랜치 목록 로컬에서도 정리
    git remote prune origin
    
    echo "================================================="
    echo "✅ 정리 완료! 현재 위치: main"
    echo "================================================="
fi
