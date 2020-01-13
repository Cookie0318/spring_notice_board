# spring_notice_board
>스프링으로 만드는 게시판

# Development Environment
* OS: Mac OS
* IDE: eclipse
* java version : 1.8
* Spring framework : 4.3.18
* Spring Security : 4.2.7
* Database : Docker container에 oracle 올려서 사용, Mybatis 사용
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
    * 비밀번호 암호화(BCypt PasswordEncoder 사용)
  * 정보 수정
  * 내가 쓴 글
# 미리보기
> 홈 화면
![홈 화면](/readme_img/home.png "홈 화면 이미지")
