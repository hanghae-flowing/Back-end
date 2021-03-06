# Backend

![KakaoTalk_20220405_214246514](https://user-images.githubusercontent.com/94155128/161756780-6a80aefe-9965-4a7f-9211-28cf3a0e9af8.png)



항해5기 D반 5조 실전프로젝트 기린이

<image src="https://img.shields.io/website?down_message=DOWN&up_message=UP&label=server&url=http://52.79.220.93:8888/health"/>  
<image src="https://img.shields.io/website?down_message=DOWN&up_message=UP&label=testServer&url=http://13.209.41.157"/>

# 📆프로젝트 기간
MVP 기간 : 22.03.04 ~ 22.04.08

유지보수 기간 : 22.04.18 ~ 22.05.29(예정)

 
<br>

## ⚙️기술스택

<img src="https://img.shields.io/badge/MYSQL-4479A1?style=for-the-badge&logo=MYSQL&logoColor=white">  <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white">  <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=white">  <img src="https://img.shields.io/badge/GitHubActions-2088FF?style=for-the-badge&logo=GitHubActions&logoColor=white">  <img src="https://img.shields.io/badge/JAVA-007396?style=for-the-badge&logo=java&logoColor=white">  <img src="https://img.shields.io/badge/Docker-4479A1?style=for-the-badge&logo=Docker&logoColor=white">  <img src="https://img.shields.io/badge/Nginx-6DB33F?style=for-the-badge&logo=Nginx&logoColor=white">  <img src="https://img.shields.io/badge/AWS-ff7f00?style=for-the-badge&logo=AWS&logoColor=white">  <img src="https://img.shields.io/badge/Oauth2.0-4479A1?style=for-the-badge&logo=Oauth2.0&logoColor=white">



## 🏄‍♂️팀원

<table>
  <tr>
    <td align="center"><a href="https://github.com/jeonbar2"><img src="https://avatars.githubusercontent.com/u/76610357?v=4" width="100px" /></a></td>
    <td align="center"><a href="https://github.com/HyoJinKin"><img src="https://avatars.githubusercontent.com/u/94884844?v=4" width="100px" /></a></td>
    <td align="center"><a href="https://github.com/John3210of"><img src="https://avatars.githubusercontent.com/u/94155128?v=4" width="100px" /></a></td>
  </tr>
  <tr>
    <td align="center"><b>강전호</b></td>
    <td align="center"><b>김효진</b></td>
    <td align="center"><b>정요한</b></td>
  </tr>

  <tr>
    <td align="center"><b>🪓Backend</b></td>
    <td align="center"><b>🔨Backend</b></td>
    <td align="center"><b>🔧Backend</b></td>
  </tr>
 <tr>
    <td align="center"><a href="https://github.com/jeonbar2">Github-Link</a></td>
    <td align="center"><a href="https://github.com/HyoJinKin">Github-Link</a></td>
    <td align="center"><a href="https://github.com/John3210of">Github-Link</a></td>
  </tr>
 
</table>



## 💬API
https://mango-pull-bbe.notion.site/API-5e13bb2a26a7482a936eaeb56450b98f



### 🖥️ERD
<img  width="1000" src = "https://user-images.githubusercontent.com/76610357/161932421-2db40f53-c58b-4db9-a20f-52e64752701c.jpg" />



### ⚙️ 아키텍쳐
<image width="800" src="https://user-images.githubusercontent.com/76610357/160749647-ba24cedc-c447-4628-9c6a-a7f92b87971b.png"/>

### 📬커밋 종류
> 수정한 종류에 따라 커밋 메시지를 선택

|메시지명|설명|
|---|---|
|feat|새로운 기능 추가 관련|
|fix|버그 수정|
|test|테스트 코드, 리팩토링 테스트 코드 추가|
|refactor|코드 리팩토링(기능향상)|
|chore|빌드 업무 수정, 패키지 매니저 수정|
|docs|문서 수정(md, git관련 파일, 이미지파일 수정)|
|style|코드 formatting, 세미콜론(;) 누락, 코드 변경이 없는 경우|

### 📢관련 이슈
> 작성한 커밋과 관련된 이슈 번호를 매핑

- 이슈 번호뒤에 아래에 써놓은 명령어를 붙여서 커밋 날리면 자동으로 이슈가 close 된다.   
`close / closes / closed / fix / fixes / fixed / resolve /resolves / resolved`
```
< 예시 >
[BE] feat: Flowing close #1
```

### 🔐보안 관련

- **(중요)** 어떠한 KEY값이나 DB 접속 정보가 포함된 커밋을 날리지 않는다.
- 한 번이라도 날리면 커밋 로그가 남아서 보안에 취약하기 때문~
- 환경변수나 json/gitignore 등의 방식을 사용해서 원격 repo에는 절대 올리지 않는다.

<br>

## 🌳Branch / PR / Issue 규칙

### Branch

- `main` 브랜치에서는 버젼 배포 외에는 작업하지 않는다.
- 브랜치 이름은 `feature-1` 이런 식으로 이슈의 번호를 명시해서 생성한다.
- `devlop` 브랜치에는 이슈단위로 기능이 구현한후에 코드리뷰후 PR한다.
- 테스트 브랜치나 더이상 안쓰는 브랜치는 삭제한다.

### Pull Request

- `develop` 브랜치에만 merge한다.
- 자신이 계획한 기능이 완료됐을 경우에만 PR 작성
- 팀원과 협의 후 PR을 작성하며 독자적으로 PR 생성 후 merge하지 않는다. 

### Issue

- 앞으로 할 일이나 버그 등을 기록한다.
- 필요한 라벨이 있다면 공동 계정(hanghae-flowing)의 `Settings` -> `Repository Defaults` 에서 추가한다.

<br>

