# book-crud-app-lab — TODO 실습 계획

원본: book-crud-app
난이도: medium
총 TODO 개수: 8

## 진행 순서

### Service
- [x] 01. `BookServiceImpl.getBookList` — 전체 도서 목록을 DAO에서 조회하여 반환한다
- [x] 02. `BookServiceImpl.getBook` — isbn으로 특정 도서를 DAO에서 조회하여 반환한다
- [x] 03. `BookServiceImpl.registBook` — 도서를 DAO에 등록한다
- [x] 04. `BookServiceImpl.removeBook` — isbn으로 도서를 DAO에서 삭제한다

### DAO
- [x] 05. `BookDaoImpl.selectAll` — 저장된 전체 도서 목록을 반환한다
- [x] 06. `BookDaoImpl.selectByIsbn` — isbn과 일치하는 도서를 반환한다. 없으면 null
- [x] 07. `BookDaoImpl.insert` — 도서를 목록에 추가한다
- [x] 08. `BookDaoImpl.deleteByIsbn` — isbn과 일치하는 도서를 목록에서 삭제한다

## 완료 후
- [x] 모든 TODO 해결 + `mvn compile` 성공
- [ ] Tomcat 실행 후 목록 조회 / 등록 / 삭제 시나리오 직접 확인
- [ ] `_solution/` 과 diff 비교 → 회고
