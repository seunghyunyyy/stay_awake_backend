# Swake Backend

> 졸음 방지 프로젝트 (Stay Awake, Swake)의 백엔드 서버  
> **Spring Boot 3.5.5 + Java 21 + MySQL** 기반 REST API

---

## 🚀 Features
- 회원가입 / 로그인 (API Key 발급)
- 세션 관리 (시작 / 종료)
- 심박수 데이터 수집
- 졸음 이벤트 기록
- 요약 통계 조회

---

## 🛠️ Tech Stack
- **Language**: Java 21
- **Framework**: Spring Boot 3.5.5
- **Database**: MySQL (H2 테스트 지원)
- **ORM**: Spring Data JPA (Hibernate)
- **Build Tool**: Gradle
- **Validation**: Jakarta Validation
- **Lombok**: Boilerplate 코드 제거

---

## 📂 Project Structure
```
src/main/java/com/swake/app
 ├─ SwakeApplication.java
 ├─ common/          # 공통 응답 / ApiKey Resolver
 ├─ domain/          # Entity & Repository
 │   ├─ user/
 │   ├─ session/
 │   ├─ hr/
 │   └─ event/
 ├─ dto/             # 요청/응답 DTO
 ├─ service/         # 비즈니스 로직
 └─ web/             # REST 컨트롤러
```

---

## ⚙️ Setup

### 1. Clone & Build
```bash
git clone https://github.com/seunghyunyyy/swake-backend.git
cd swake-backend
./gradlew build
```

### 2. Database (MySQL)
```sql
CREATE DATABASE swake CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE USER 'swake'@'localhost' IDENTIFIED BY 'swake1234';
GRANT ALL PRIVILEGES ON swake.* TO 'swake'@'localhost';
FLUSH PRIVILEGES;
```

### 3. application.properties
```properties
spring.application.name=swake
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/swake?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Seoul&characterEncoding=UTF-8
spring.datasource.username=swake
spring.datasource.password=swake1234
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### 4. Run
```bash
./gradlew bootRun
```
서버 실행 후 접속: `http://localhost:8080`

---

## 📡 API Endpoints

### Auth
- **회원가입**  
  `POST /api/auth/signup`  
  Body: `{"email":"...","password":"..."}`

- **로그인**  
  `POST /api/auth/signin`  
  Body: `{"email":"...","password":"..."}`

### Session
- **세션 시작**  
  `POST /api/sessions/start`  
  Header: `X-API-KEY`  
  Body: `{"deviceLabel":"..."}`

- **세션 종료**  
  `POST /api/sessions/end`  
  Header: `X-API-KEY`

### Ingest
- **심박수 전송**  
  `POST /api/ingest/heart-rate`  
  Body: `{"source":"WATCH","bpm":78}`

- **졸음 이벤트 전송**  
  `POST /api/ingest/drowsiness`  
  Body:
  ```json
  {
    "source": "CAMERA",
    "severity": "MEDIUM",
    "score": 63,
    "note": "eyes closed 2s"
  }
  ```

### Stats
- **요약 통계**  
  `GET /api/stats/summary`

---

## 🧪 Quick Test (cURL)

```bash
# 회원가입
curl -X POST http://localhost:8080/api/auth/signup \
  -H "Content-Type: application/json" \
  -d '{"email":"test@swake.ai","password":"pw1234"}'

# 로그인 (apiKey 확인)
curl -X POST http://localhost:8080/api/auth/signin \
  -H "Content-Type: application/json" \
  -d '{"email":"test@swake.ai","password":"pw1234"}'

# 세션 시작
curl -X POST http://localhost:8080/api/sessions/start \
  -H "Content-Type: application/json" \
  -H "X-API-KEY: <apiKey>" \
  -d '{"deviceLabel":"galaxy-s24"}'

# 심박 전송
curl -X POST http://localhost:8080/api/ingest/heart-rate \
  -H "Content-Type: application/json" \
  -H "X-API-KEY: <apiKey>" \
  -d '{"source":"WATCH","bpm":78}'

# 졸음 이벤트 전송
curl -X POST http://localhost:8080/api/ingest/drowsiness \
  -H "Content-Type: application/json" \
  -H "X-API-KEY: <apiKey>" \
  -d '{"source":"CAMERA","severity":"MEDIUM","score":63,"note":"eyes closed 2s"}'

# 통계 조회
curl http://localhost:8080/api/stats/summary
```

---

## 📌 TODO (Next Steps)
- [ ] FCM Push 알림 연동
- [ ] JWT 기반 인증 적용
- [ ] Dockerfile & docker-compose 배포 환경 구성
- [ ] 심박수/졸음 이벤트 통계 상세화

---

## 📄 License
MIT License
