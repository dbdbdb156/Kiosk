# 차박 용품점 키오스크

### 요구사항
#### 관리자
1. 관리자는 로그인을 한다.
- 로그인한 관리자는 주문내역을 볼 수 있다
- 회원가입한 다른 회원들의 정보를 확인할수 있다.
- 관리자는 재고를 확인할수 있고, 각 재고의 좋아요 수를 확인할수 있다.
2. 관리자가 회원가입을 할 때 Oauth로 인증을 한다.

#### 사용자 
1. 사용자가 로그인 할 때 Oauth로 인증을 한다.
2. 사용자는 주문을 한다.
3. 주문 후에는 각 메뉴에 좋아요를 누를수 있다

- 차박 용품 : 텐트

### 기술 스택
1. SpringBoot : MVC 모델
2. DB
- MySQL(version = MySQL 8.0) : JPA, QueryDSL
- Redis : JPA
3. Oauth2 : Spring Security

### 기술 버전 및 참조 URL
#### Redis 
- version : 3.2.100
- 다운로드 : https://github.com/microsoftarchive/redis
#### MySQL
- version : MySQL 8.0
- 다운로드 : https://dev.mysql.com/doc/relnotes/mysql/8.0/en/

#### 기능 URL 설명
1. 로그인 
- URL : {domain}/ : 기본 페이지, 로그인, 로그아웃 및 기능 구현
- URL : {domain}/login : OAuth2 를 통한 Google,Naver 인증
2. 상품구매
- URL : {domain}/order : form 을 통한 상품 구매
3. 상품구매내역
- URL : {domain}/orders : 구매한 상품 리스트
4. 회원리스트
- URL : {domain}/members : 로그인한 회원 리스트
5. 아이템 리스트 및 종아요
- URL : {domain}/items : 아이테 리스트 및 좋아요 수
- URL : {domain}/items/{itemid} : Redis를 이용한 좋아요 선택 및 취소
