# spring_notice_board
>스프링으로 만드는 게시판

# Development Environment
* OS: Mac OS
* IDE: eclipse
* jdk version : 1.8
* Spring framework : 4.3.18
* Spring Security : 4.2.7
* Database : Oracle DB(Docker container), Mybatis
* Design : Bootstrap 4

# Overview
* 게시판
  * 목록
    * 페이징, 글쓰기
  * 게시글 읽기
    * 작성자의 경우 - 수정, 삭제
    * 관리자의 경우 - 삭제
    * 그 외 - 읽기
    
* 회원 
  * 로그인
    * Spring security를 사용한 인증작업, 커스텀 로그인 페이지 사용
  * 회원 가입
    * 비밀번호 암호화(Spring Security - BCypt PasswordEncoder 사용)
    * 유효화 검사(jQuery 사용)
  * 정보 수정
  * 내가 쓴 글
# 미리보기
> **홈 화면**

![홈 화면](/readme_img/home.png "홈 화면 이미지")

> **회원 가입** 
![회원 가입](/readme_img/join.png "회원가입 이미지")

> **로그인**

![로그인 페이지](/readme_img/login.png "로그인 이미지")

> **게시글 목록**

![목록 페이지](/readme_img/list.png "목록 페이지")

> **세부 화면 - 관리자**

![게시글 읽기](/readme_img/detail_admin.png "관리자 게시글 읽기")
