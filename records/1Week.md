## 필수 미션 1 : 회원 가입 / 로그인

- [x] **엔드포인트**
    - [x] GET /member/join : 가입 폼
    - [x] POST /member/join : 가입 폼 처리
    - [x] GET /member/login : 로그인 폼
    - [x] POST /member/login : 로그인 폼 처리
    - [x] POST /member/logout : 로그아웃

- [x] **폼**
    - [x] 회원가입 폼
        - [x] username
        - [x] password
        - [x] passwordConfirm
    - [x] 로그인 폼
        - [x] username
        - [x] password

## 필수 미션 2 : 글 CRUD

- [ ] **엔드포인트**
    - [ ] GET / : 홈
    - [x] GET /post/list : 전체 글 리스트
    - [ ] GET /post/myList : 내 글 리스트
    - [x] GET /post/1 : 1번 글 상세보기
    - [x] GET /post/write : 글 작성 폼
    - [x] POST /post/write : 글 작성 처리
    - [x] GET /post/modify/1 : 1번 글 수정 폼
    - [x] PUT /post/modify/1 : 1번 글 수정 폼 처리
    - [x] DELETE /post/delete/1 : 1번 글 삭제
    - [ ] GET /b/user1 : 회원 user1 의 전체 글 리스트
    - [ ] GET /b/user1/3 : 회원 user1 의 글 중에서 3번글 상세보기

- [ ] **폼**
    - [ ] 글 쓰기 폼
        - [x] title
        - [x] body
        - [ ] isPublished (체크박스, value="true")
    - [ ] 글 수정 폼
        - [x] title
        - [x] body
        - [ ] isPublished (체크박스, value="true")

## 선택 미션 3 : 댓글 CRUD

- [x] **엔드포인트**
    - [x] 댓글 목록 : 글 상세페이지 하단 (예: 5번글에 대한 댓글 작성 폼)
    - [x] 댓글 작성
        - [x] 글 상세페이지 하단 : 5번글에 대한 댓글 작성 폼
        - [x] POST /post/comment/write/5 : 5번글에 대한 댓글 작성 폼 처리
    - [x] 댓글 수정
        - [x] GET /post/5/comment/6/modify  : 5번글에 대한 6번 댓글 수정 폼
        - [x] POST /post/5/comment/6/modify : 5번글에 대한 6번 댓글 수정 폼 처리
    - [x] 댓글 삭제
        - [x] DELETE /post/5/comment/delete/6 : 5번글에 대한 6번 댓글 삭제

- [x] **폼**
    - [x] 댓글 작성 폼
        - [x] body
    - [x] 댓글 수정 폼
        - [x] body