# book-final-lab — TODO 실습 계획

원본: book-final
난이도: medium
총 TODO 개수: 71

## 진행 순서

- [ ] 01. BookController.doGet                — GET 요청을 처리한다.
- [ ] 02. BookController.logout               — logout 메서드를 구현한다.
- [ ] 03. BookController.doPost               — POST 요청을 처리한다.
- [ ] 04. BookController.login                — login 메서드를 구현한다.
- [ ] 05. BookController.list                 —  을(를) 조회한다.
- [ ] 06. BookController.detail               — detail 메서드를 구현한다.
- [ ] 07. BookController.regist               — regist 메서드를 구현한다.
- [ ] 08. BookController.delete               —  을(를) 삭제한다.
- [ ] 09. LoginFilter.doFilter                — 필터 처리 로직을 구현한다.
- [ ] 10. BookServiceImpl.getInstance         — instance 을(를) 조회한다.
- [ ] 11. BookServiceImpl.getBookList         — book list 을(를) 조회한다.
- [ ] 12. BookServiceImpl.getBook             — book 을(를) 조회한다.
- [ ] 13. BookServiceImpl.registBook          — registBook 메서드를 구현한다.
- [ ] 14. BookServiceImpl.removeBook          — book 을(를) 삭제한다.
- [ ] 15. BookServiceImpl.login               — login 메서드를 구현한다.
- [ ] 16. BookDaoImpl.getInstance             — instance 을(를) 조회한다.
- [ ] 17. BookDaoImpl.selectAll               — all 을(를) 조회한다.
- [ ] 18. BookDaoImpl.selectByIsbn            — by isbn 을(를) 조회한다.
- [ ] 19. BookDaoImpl.insert                  —  을(를) 저장한다.
- [ ] 20. BookDaoImpl.deleteByIsbn            — by isbn 을(를) 삭제한다.
- [ ] 21. BookDaoImpl.selectMemberById        — member by id 을(를) 조회한다.
- [ ] 22. Book.getIsbn                        — isbn 을(를) 조회한다.
- [ ] 23. Book.setIsbn                        — isbn 을(를) 수정한다.
- [ ] 24. Book.getTitle                       — title 을(를) 조회한다.
- [ ] 25. Book.setTitle                       — title 을(를) 수정한다.
- [ ] 26. Book.getAuthor                      — author 을(를) 조회한다.
- [ ] 27. Book.setAuthor                      — author 을(를) 수정한다.
- [ ] 28. Book.getPrice                       — price 을(를) 조회한다.
- [ ] 29. Book.setPrice                       — price 을(를) 수정한다.
- [ ] 30. Book.toString                       — toString 메서드를 구현한다.
- [ ] 31. Member.getId                        — id 을(를) 조회한다.
- [ ] 32. Member.setId                        — id 을(를) 수정한다.
- [ ] 33. Member.getPassword                  — password 을(를) 조회한다.
- [ ] 34. Member.setPassword                  — password 을(를) 수정한다.
- [ ] 35. Member.getName                      — name 을(를) 조회한다.
- [ ] 36. Member.setName                      — name 을(를) 수정한다.
- [ ] 37. Member.toString                     — toString 메서드를 구현한다.
- [ ] 38. detail.jsp                          — root 값을 EL로 출력한다.
- [ ] 39. detail.jsp                          — book.isbn 값을 EL로 출력한다.
- [ ] 40. detail.jsp                          — book.title 값을 EL로 출력한다.
- [ ] 41. detail.jsp                          — book.author 값을 EL로 출력한다.
- [ ] 42. detail.jsp                          — book.price 값을 EL로 출력한다.
- [ ] 43. detail.jsp                          — root 값을 EL로 출력한다.
- [ ] 44. detail.jsp                          — book.isbn 값을 EL로 출력한다.
- [ ] 45. list.jsp                            — root 값을 EL로 출력한다.
- [ ] 46. list.jsp                            — root 값을 EL로 출력한다.
- [ ] 47. list.jsp                            — bookList 값을 EL로 출력한다.
- [ ] 48. list.jsp                            — root 값을 EL로 출력한다.
- [ ] 49. list.jsp                            — book.isbn 값을 EL로 출력한다.
- [ ] 50. list.jsp                            — book.isbn 값을 EL로 출력한다.
- [ ] 51. list.jsp                            — book.title 값을 EL로 출력한다.
- [ ] 52. list.jsp                            — book.author 값을 EL로 출력한다.
- [ ] 53. list.jsp                            — book.price 값을 EL로 출력한다.
- [ ] 54. regist.jsp                          — root 값을 EL로 출력한다.
- [ ] 55. regist.jsp                          — root 값을 EL로 출력한다.
- [ ] 56. 404.jsp                             — root 값을 EL로 출력한다.
- [ ] 57. 500.jsp                             — root 값을 EL로 출력한다.
- [ ] 58. header.jsp                          — pageContext.servletContext.contextPath 값을 EL로 출력한다.
- [ ] 59. header.jsp                          — root 값을 EL로 출력한다.
- [ ] 60. header.jsp                          — root 값을 EL로 출력한다.
- [ ] 61. header.jsp                          — empty sessionScope.loginMember 값을 EL로 출력한다.
- [ ] 62. header.jsp                          — root 값을 EL로 출력한다.
- [ ] 63. header.jsp                          — not empty sessionScope.loginMember 값을 EL로 출력한다.
- [ ] 64. header.jsp                          — session 속성 loginMember.name 을(를) EL로 출력한다.
- [ ] 65. header.jsp                          — root 값을 EL로 출력한다.
- [ ] 66. login.jsp                           — pageContext.servletContext.contextPath 값을 EL로 출력한다.
- [ ] 67. login.jsp                           — not empty sessionScope.alertMsg 값을 EL로 출력한다.
- [ ] 68. login.jsp                           — session 속성 alertMsg 을(를) EL로 출력한다.
- [ ] 69. login.jsp                           — root 값을 EL로 출력한다.
- [ ] 70. login.jsp                           — savedId 쿠키의 값을 EL로 출력한다. (cookie 내장 객체 활용)
- [ ] 71. login.jsp                           — empty cookie.savedId? "" : "checked" 값을 EL로 출력한다.

## 완료 후

- [ ] 모든 TODO 해결 + 컴파일 성공
- [ ] 직접 실행해서 시나리오 확인
- [ ] `_solution/` 과 diff 비교 후 회고
