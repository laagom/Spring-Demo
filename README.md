# Spring-Demo

Spring Boot로 만든 **게시판 CRUD 학습 프로젝트**입니다.
같은 게시판 기능을 **MyBatis**와 **Spring Data JPA + QueryDSL** 두 가지 방식으로 구현해 비교했습니다.

## 기술 스택

| 구분 | 내용 |
|---|---|
| Language · Build | Java 17, Gradle |
| Framework | Spring Boot 3.2.5 (Web, Validation) |
| 데이터 접근 | MyBatis 3.0.3, Spring Data JPA, QueryDSL 5.0 |
| DB | MariaDB (HikariCP) |
| 기타 | MapStruct (Entity ↔ DTO 변환), Lombok, P6Spy (SQL 로그 출력) |

## 구조

```text
src/main/java/lagom/demo/
├─ board/
│  ├─ controller/   BoardController (MyBatis), BoardJpaController (JPA), HelloController (요청 파라미터 연습)
│  ├─ service/      BoardService, BoardJpaService
│  ├─ mapper/       BoardMapper (MyBatis)
│  ├─ repository/   BoardRepository, custom/BoardRepositoryCustomImpl (QueryDSL)
│  ├─ entity/       Board, BoardType
│  └─ dto/          BoardDTO, mapping/BoardDtoMapping (MapStruct)
└─ global/config/   JpaConfig, MybatisConfig, P6SpySqlFormatter
src/main/resources/mybatis/   BoardMapping.xml (MyBatis SQL 매핑)
```

## API

| 방식 | 메서드 · 경로 | 기능 |
|---|---|---|
| MyBatis | `GET /boards` | 게시글 목록 |
| | `GET /boards/{boardId}` | 게시글 조회 |
| | `POST /boards` | 게시글 등록 |
| | `PUT /boards/{boardId}` | 게시글 수정 |
| | `DELETE /boards/{boardId}` | 게시글 삭제 |
| JPA | `GET /jpaBoards` | 게시글 목록 (키워드 검색) |
| | `GET /jpaBoards/{boardId}` | 게시글 조회 |
| | `POST /jpaBoards` | 게시글 등록 |
| | `PUT /jpaBoards/{boardId}` | 게시글 수정 |

## 구현 포인트

- 같은 도메인(`edu_board` 테이블)을 **MyBatis(XML 매퍼)** 와 **JPA** 로 각각 구현
- **QueryDSL**로 동적 조건 조회 구현, 조인·아우터 조인·exists 쿼리를 테스트(`QueryDslTest`)
- **MapStruct**로 Entity ↔ DTO 변환 코드를 자동 생성
- **P6Spy**로 실행되는 SQL을 보기 좋게 출력

## 실행 방법

1. MariaDB를 준비하고 `src/main/resources/application.properties`의 접속 정보(`jdbc-url`, `username`, `password`)를 본인 환경에 맞게 수정합니다.
2. 게시글 테이블(`edu_board`)을 생성합니다. (테이블 생성 스크립트는 포함되어 있지 않습니다.)
3. 아래 명령으로 실행합니다.

```bash
./gradlew bootRun
```
