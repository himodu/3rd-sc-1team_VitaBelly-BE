# 3rd-sc-1team_VitaBelly-BE

## 프로젝트 설명
VitaBelly 프로젝트는 2024 Google Solution Challenge 참가를 위한 프로젝트이다.


본 레포지토리는 해당 서비스의 동작을 위한 api를 제공하는 backend API 서버로<br/>
구글 소셜 로그인, 회원가입기능 제공<br/>
임신과 출산에 관련된 전반적인 정보를 제공함</br>
임신 주차별 발달 과정, 건강 관리 팁, 필수 검사 일정 등</br>
- 위치 정보 제공 (Google Maps) 사용자의 위치를 기반으로 한 가까운 병원, 산후 조리원, 육아 지원 센터 등의 정보와 리뷰 제공</br>
- 건강 관리 기록 및 추적 (Google Fitness) 임산부의 건강 데이터를 기록하고 추적하는 기능을 제공함</br>
- 가사 도우미 등록을 통해 쉽게 도움을 받을 수 있는 서비스</br>

### API 명세서 (Swagger)
<a href="http://34.64.158.156:8080/swagger-ui/index.html"><img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=black"></a>

### 사용된 기술
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white">  
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white">  
<img src="https://img.shields.io/badge/Google Cloud-4285F4?style=for-the-badge&logo=Google Cloud&logoColor=white">  
<img src="https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=Firebase&logoColor=white"> 

### 인증방식

구글 클라우드 api 를 이용하여 구글 소셜 로그인 을 구현

아래 uri 양식으로 <br/>
https://accounts.google.com/o/oauth2/auth?client_id=###&redirect_uri=###/auth/login&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile

접속 후 로그인하면 Firebase Auth 를 적용하여 응답 json에서 idtoken value를 함께 반환한다.

해당 idtoken 을 요청헤더에 Authrization 이라는 이름으로 저장하면 스프링 시큐리티를 적용하여 사용자를 인증하고 인증된 사용자로서 서비스 api 자원을 사용할 수 있다.
