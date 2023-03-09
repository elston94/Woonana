# Woonana 프로젝트

## Woonana 소개 및 시연 영상

시나리오가 궁금하면 [여기]()에서 확인할 수 있습니다.

## Overview

운동을 하고 싶지만 친구들과 모이기 힘든가요?<br>
가볍게 주변에서 운동을 하고 싶은신가요?<br>
저희 **Woonana** 에서 다양한 사람들과 주변에서 운동을 즐겨보세요!!

## 팀 소개

|  Member  |  김응철   |  김석원   |  배나영   |  유우식   |  정채은   |  허범   |
| :------: | :-------: | :-------: | :-------: | :-------: | :-------: | :-----: |
| Profile  | ![응철]() | ![석원]() | ![나영]() | ![우식]() | ![채은]() | ![범]() |
| Position | BE & Chat |    FE     |    BE     |    FE     |    BE     |   BE    |

## 프로젝트 기간 - 2022.04.11 ~ 2022.05.20

### 주요 기능

---

- 설명 : 주변에서 운동할 사람을 손쉽게 구해 같이 운동할 수 있게 도와주는 서비스
- 주요 기능 :
  - 장소와 시간을 정해서 사람을 모집할 수 있음
  - 채팅을 통해 운동 모임 전에 대화를 할 수 있음
  - 운동이 끝난 후에 평가를 통해 모임에 참여했던 사람들을 평가할 수 있음
  - 운동 기록을 전체기록, 월별기록, 그래프 등으로 제공해서 확인가능

### 개발 환경

---

![기술스택](README.assets/%EA%B8%B0%EC%88%A0%EC%8A%A4%ED%83%9D.png)

**Backend**

- IntelliJ <img src="https://img.shields.io/badge/IntelliJ-000000?style=flat-square&logo=IntelliJIDEA&logoColor=white"/>
- Spring Boot <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-square&logo=Spring Boot&logoColor=white"/>
- JAVA <img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=white"/>
- MySQL <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/>
- AWS EC2 <img src="https://img.shields.io/badge/AWS EC2-232F3E?style=flat-square&logo=Amazon AWS&logoColor=white"/>

**Frontend**

- Visual Studio Code <img src="https://img.shields.io/badge/Visual Studio Code-007ACC?style=flat-square&logo=Visual Studio Code&logoColor=white"/>
- Vue <img src="https://img.shields.io/badge/Vue.js-4FC08D?style=flat-square&logo=vue.js&logoColor=black"/>

**CI/CD**

- AWS EC2 <img src="https://img.shields.io/badge/AWS EC2-232F3E?style=flat-square&logo=Amazon AWS&logoColor=white"/>
- Docker <img src="https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=Docker&logoColor=black"/>
- Nginx <img src="https://img.shields.io/badge/nginx-009639?style=flat-square&logo=nginx&logoColor=black"/>

**협업 툴**

- Git <img src="https://img.shields.io/badge/git-F05032?style=flat-square&logo=Git&logoColor=white"/>
- Jira <img src="https://img.shields.io/badge/jira-0052CC?style=flat-square&logo=Jirasoftware&logoColor=white"/>
- Notion <img src="https://img.shields.io/badge/Notion-000000?style=flat-square&logo=Notion&logoColor=white"/>
- Mattermost <img src="https://img.shields.io/badge/Mattermost-0058CC?style=flat-square&logo=Mattermost&logoColor=white"/>
- Webex

### 기술 스택

---

![서비스흐름도](README.assets/%EC%84%9C%EB%B9%84%EC%8A%A4%ED%9D%90%EB%A6%84%EB%8F%84.png)

### Git Flow 브랜치 전략

---

- Git Flow Model을 사용하고, Git 기본 명령어를 사용한다.
- commit은 git-cz를 사용해서 commit한다.
- Git Flow 사용 브랜치
  - feature - 기능
  - develop - 개발
  - master - 배포
- Git Flow 진행 방식
  1. feature 브랜치가 완성되면 develop 브랜치로 pull request를 요청한다.
  2. pull request가 요청되면, 모든 팀원이 코드 리뷰를 하고 merge를 진행한다.
  3. 매주 금요일 develop 브랜치를 master 브랜치로 병합하여 배포를 진행한다.

### Jira

---

일정 및 효율적인 개발을 위해 Jira를 사용했습니다. 매주 금요일 미팅을 통해 다음 주에 진행될 주 단위 계획을 짜고 이슈들을 스프린트에 등록했습니다.
스프린트는 주 단위로 만들어 진행했습니다.

- Epic : 구현할 기능들을 큰 틀로 나누어서 구성하였습니다.
- Story : 세부 기능들을 작성하여 Epic을 지정하였습니다.

### Notion & Webex

---

Notion에서는 모두가 봐야할 공지, 미팅내용, 학습내용, 일정, 필수 링크들을 모아서 정리했습니다.

Webex에서는 항상 모여서 개발을 진행하고, 회의를 진행했습니다.

### Scrum

---

매일 반 미팅 이후에 팀원끼리 모여 시간을 가졌습니다. Scrum을 통해 팀원들의 진행 상황을 확인하고 꾸준히 모니터링 했습니다.

### ER Diagram

---

![자율플젝ERD](README.assets/ERD%20%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8.png)
