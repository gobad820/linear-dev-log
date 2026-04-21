# 🛠 Linear-Git 연동 설정 가이드

이 문서는 로컬 개발 환경과 Linear 이슈를 완벽하게 연동하기 위한 설정 단계를 설명합니다.

## 1. 브랜치 명명 규칙 (Branch Naming)
Linear는 브랜치 이름에 포함된 **이슈 키(Issue Key)**를 식별하여 자동으로 연결합니다.
- **형식:** `[이슈키]/[간략한설명]` 또는 `feature/[이슈키]-[설명]`
- **예시:** `SSA-50/jsp-lifecycle-setup`

## 2. 커밋 메시지를 통한 이슈 제어
커밋 메시지에 특정 키워드를 사용하여 Linear 이슈 상태를 자동으로 변경할 수 있습니다.
- **이슈 종료:** `Fixes SSA-50`, `Closes SSA-50` (PR 머지 시 자동 완료)
- **이슈 연결:** `Ref SSA-50` (이슈에 커밋 링크만 추가)

## 3. 로컬 자동화 스크립트 활용
`/home/ssafy/linear-dev-log/linear-integration/linear_git_sync.js` 스크립트를 사용하여 현재 작업 중인 브랜치의 우선순위를 실시간으로 조정할 수 있습니다.

### 실행 방법:
```bash
# 개발 시작 시 실행
node /home/ssafy/linear-dev-log/linear-integration/linear_git_sync.js
```
이 스크립트는 현재 Git 브랜치에서 이슈 키를 추출하여 Linear 상의 해당 이슈 우선순위를 **P1 (Urgent)**으로 즉시 격상시킵니다.

## 4. GitHub/GitLab 연동 설정 (관리자 권한 필요)
1. Linear 웹 앱의 **Settings > Integrations**로 이동합니다.
2. **GitHub** 또는 **GitLab**을 선택하고 레포지토리를 연결합니다.
3. 'Link issues to pull requests' 옵션을 활성화하여 자동 상태 변경 기능을 사용하세요.
