# 일정 관리 프로젝트
유저의 일정을 관리하고 저장해주는 웹 어플리케이션입니다.

## 개발환경
- Java 17
- Spring boot 3.4.5
- Mysql 8.0
- Git

# 프로그램 동작 방식
1. 클라이언트 요청이 들어오면 로그인 필터를 통해 세션이 존재하는지 확인하여 로그인이나 유저 생성의 요청이 아닌 경우 예외 처리합니다.
2. Controller에서 해당 요청에 맞는 로직을 실행합니다. (요청 조건에 적합하지 않은 요청은 예외 처리를 진행합니다.)
3. Service에서 요청을 수행하기 위한 동작을 진행합니다. 필요한 데이터 정보가 있는 경우 Repository에게 요청합니다.
4. Repository에서 SpringJpa를 통해 조회된 데이터 정보를 Service에게 전달합니다.
5. Service는 받은 데이터와 요청 간의 인증 오류가 없는지 확인 후 요청을 진행 후 결과를 Controller에게 전달합니다.
6. 결과를 Response body에 담아 클라이언트에게 전달합니다.

# API
![ScheduleDevelopAPI](https://github.com/user-attachments/assets/b5103543-6319-4689-848b-226472342b55)

# ERD

![ScheduleDevelopERD](https://github.com/user-attachments/assets/f6ecdf17-3c4f-4683-b702-0941849c24f7)

