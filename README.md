# 🚀 Linear Dev Log

이 저장소는 **Linear(계획)**와 **GitHub(실행)**를 연동하여 학습 성장을 관리하는 마스터 레포지토리입니다.

---

## 📁 프로젝트 구조 (Monorepo)

- **[backend/](./backend)**: Java Backend Fundamentals (Servlet, JSP, Spring MVC)
- **[algorithm/](./algorithm)**: Algorithm & Coding Test (BOJ, Programmers)
- **[database/](./database)**: Database Deep Dive (SQL, Transaction, MVCC)
- **[linear-integration/](./linear-integration)**: Linear-Git 동기화 및 관리 자동화 툴

---

## 🛠 Linear 연동 가이드

학습을 시작할 때 다음의 워크플로우를 권장합니다.

1. **이슈 확인**: Linear 웹/앱에서 오늘 할 일(Priority 1)을 확인합니다.
2. **브랜치 생성**: 해당 이슈 번호로 브랜치를 만듭니다.
   ```bash
   git checkout -b feature/SSA-50-jsp-lifecycle
   ```
3. **학습 및 코딩**: 해당 폴더(예: `backend/`)에서 작업합니다.
4. **동기화**: 개발 도중 우선순위를 높이려면 스크립트를 실행합니다.
   ```bash
   node ./linear-integration/linear_git_sync.js
   ```
5. **완료 및 커밋**: 커밋 메시지에 이슈를 닫는 키워드를 포함합니다.
   ```bash
   git commit -m "backend: JSP 내장객체 학습 완료 (Fixes SSA-50)"
   ```

---

## 💡 유용한 팁
- 모든 학습 파일(`.md`, `.java`, `.sql`)은 이 저장소에서 한꺼번에 관리됩니다.
- **[Linear Integration Setup](./linear-integration/GIT_INTEGRATION_SETUP.md)**에서 상세 설정을 확인할 수 있습니다.
