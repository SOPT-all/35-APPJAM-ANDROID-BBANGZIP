# 🍞35-APPJAM-ANDROID-BBANGZIP🍞
> 미룬이 대학생을 위한 스터디 플래너, 🍞제과제빵점🍞

<img width="1920" alt="서비스 요약" src="https://github.com/user-attachments/assets/97460a2a-1d9c-4956-b36c-ce83ef5eeb58">
<img width="1920" alt="앱잼 내 구현하게 될 MVP 기능" src="https://github.com/user-attachments/assets/0188f116-a55b-4eba-92c7-7b197df67f25">

<br>

## _**👐🏻 Contributors**_
| 하지은(Lead) <br> [@HAJIEUN02](https://github.com/HAJIEUN02) | 김재민 <br> [@kamja0510](https://github.com/kamja0510) | 이승범 <br>[@beom84](https://github.com/beom84) | 이준희 <br> [@l2zh](https://github.com/l2zh)
|:---:| :---: | :---: | :---: |
| <img width="250" src="https://avatars.githubusercontent.com/u/83916472?v=4"/> | <img width="250" src="https://avatars.githubusercontent.com/u/89302303?v=4"/> | <img width="250" src="https://avatars.githubusercontent.com/u/127933902?v=4"/> | <img width="250" src="https://avatars.githubusercontent.com/u/113578158?v=4"/> |
| `로그인 및 온보딩`, `마이페이지` |`과목 관리` |`오늘 할 일`, `마이페이지`| `명예오비` |

<br>

## _**🍀 우리만의 협업 룰**_

> ### ✨ 그라운드룰

```
1️⃣ 매일매일 상황 공유하기
2️⃣ 혼자 고민하는 시간에 스스로 제한을 두고, 해결하지 못한 경우 팀원들과 함께 고민하기
3️⃣ 불만이 생기는 경우 바로 대화하기. 피드백 수용하기!
4️⃣ 질문 전에 꼭 먼저 고민해보기
5️⃣ 늘 둥글고 예쁜 말투로 협업하기
6️⃣ 서로 칭찬 많이 해주기
```

<br>

> ### 🧑🏻‍💼 작업 트래킹 방식
> O-KR-ACT-TASK를 활용한 노션 작업 트래킹, Github Projects 활용
```
- O(Object): 목표
- KR(Key Result): 핵심 결과 (목표 달성을 위해 내놓아야 하는)
- Act: 행동 (핵심 결과를 도출하기 위해 해야 하는)
- Task: 업무 (행동을 이루는 작은 업무 단위)
```
[🔗NOTION TRACKING](https://southern-comet-4a3.notion.site/029d0862b9d642608724793b051c6205?v=28b00ad725704efca8b1f8a128f534e0)<br>
[🔗GITHUB PROJECTS](https://github.com/orgs/SOPT-all/projects/17)

<br>

> ### ✍🏻 코드 리뷰 진행 방식 및 PR Merge 기준

```
1️⃣ PR 올라온 뒤 12시간(자는 시간 제외)내로 모두 코리 달기(comment로 달 것!)
- 이 때, pn룰을 적용하며 p2까지 필수적으로 반영한다.
2️⃣ 코리 반영은 1.5일 내로 할 것(코리 반영 후 다시 알리면 12시간 내로 확인하고 approve 달기)
3️⃣ PR Merge는 2명의 approve가 있는 경우에만 가능!
```

<br>

##  _**📚 CONVENTION**_

[☀️Git Convention & Branch Strategy](https://www.notion.so/Git-Convention-Branch-Strategy-269201ee3aea4f0f831637b39b10b13a) <br>
[🌧️Android Coding Convention](https://www.notion.so/Android-Coding-Convention-fefe420f40764d51b614ee8032154217) <br>
[🌀Package Convention](https://www.notion.so/Package-Convention-e3a113cc081f4b2fa911eb085b3a229c) <br>
[❄️Issue & PR Convetion](https://www.notion.so/ISSUE-PR-Convention-67748aabab174396ae37f02ebf2cef5e)

<br>


<br>

##  _**🖥️ TECH STACKS**_

<b>Environment</b>

<img src="https://img.shields.io/badge/android-34a853?style=for-the-badge&logo=android&logoColor=white"> <img src="https://img.shields.io/badge/android studio-3DDC84?style=for-the-badge&logo=androidstudio&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">

<b>Development</b>

<img src="https://img.shields.io/badge/kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white"> <img src="https://img.shields.io/badge/jetpack compose-4285F4?style=for-the-badge&logo=jetpack compose&logoColor=white">

<b>Test</b>

<img src="https://img.shields.io/badge/github actions-2088FF?style=for-the-badge&logo=github actions&logoColor=white">

<b>Communication</b>

<img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white"> <img src="https://img.shields.io/badge/figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white"> <img src="https://img.shields.io/badge/kakaotalk-FFCD00?style=for-the-badge&logo=kakaotalk&logoColor=white"> <img src="https://img.shields.io/badge/discord-5865F2?style=for-the-badge&logo=discord&logoColor=white">

<b>Technology</b>
| Category | Content | 선정 이유 |
| --- | --- | --- |
| Architecture | SAA(Single Activity Architecture), Clean Architecture(+ UseCase) | 각 layer의 명확한 분리를 통해 가독성과 유지보수성을 높이고, Jetpack Navigation과의 통합을 통해 화면 간 상태 관리를 효율적으로 처리합니다. |
| Design Pattern | Repository Pattern, MVI | 데이터 소스와 비즈니스 로직을 분리하여 데이터 접근 로직을 캡슐화합니다. 단방향 데이터 흐름을 통해 상태를 예측 가능하게 관리합니다. |
| Dependency Injection | Hilt | 모듈 간 결합도를 낮추고 DI 과정을 간소화하여 생산성을 높이고 테스트를 용이하게 합니다. |
| Network | Retrofit, OkHttp | Retrofit의 직관적인 API 통신 인터페이스와 OkHttp의 세부 설정과 로깅을 통해 안정적이고 유연한 네트워크 통신을 할 수 있습니다. |
| Asynchronous Processing | Coroutine(+Flow) | 비동기 작업을 직관적으로 처리하고 데이터 스트림을 효율적으로 관리합니다. UI 상태와 데이터를 동기화합니다. |
| Third Party Library | Timber, kotlinSerialization | 로그 관리와 네트워크 처리 성능을 최적화합니다. |
| Strategy | Git Flow | 명확한 Git 전략으로 협업 효율성을 높입니다. |
| CI | GitHub Action(KtLint, Complie Check) | 코드 품질과 일관성을 유지하고 빌드 오류를 사전에 방지합니다. |
