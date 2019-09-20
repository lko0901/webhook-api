

# WebHook Api Server with Spring-Boot

여러 위치와 여러 형태로 흩어져 있는 WebHook Script들을 한군데 모아 관리하기 위한 Spring Boot 기반의 WebHook Api

### Installing

gradle 기반으로 Excutable Jar 생성 후 실행 시 아래 명령어 수행

```
./gradlew bootJar
java -jar ./build/libs/webhook-api-0.0.1-SNAPSHOT.jar
```

바로 실행 시킬 시 아래 명령어 수행

```
./gradlew bootRun
```

실행 후 아래 주소로 접속하여 api 확인 가능
```
http://localhost:8080/swagger-ui.html
```

### Usage
#### Application setting(by yaml)

```
spring:
  profiles: jandi

jandi:
  headers:
    Accept: application/vnd.tosslab.jandi-v2+json
    Content-Type: application/json
  webHooks:
    - alias: monitoring
      url: https://wh.jandi.com/connect-api/webhook/11111111/11111111111111111111111111111111
      template: '{ "body": "monitoring alert", "connectColor": "#FAC11B", "connectInfo": [ { "title": "모니터링", "description": "{{monitoring_description}}" } ]}'
    - alias: twotwo
      url: https://wh.jandi.com/connect-api/webhook/22222222/22222222222222222222222222222222
      template: '{ "body": "{{body}}", "connectColor": "#FAC11B", "connectInfo": [ { "title": "{{title}}", "description": "{{description}}" } ]}'

```

```
spring:
  profiles: slack

slack: TODO
```

```
spring:
  profiles: telegram

telegram: TODO
```

#### Jandi(잔디)
등록된 alias 목록 조회
```
GET http://localhost:8080/jandi/aliases
Content-Type: application/json

HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Fri, 20 Sep 2019 01:03:11 GMT

{
  "monitoring": "{  \"monitoring_description\": \"\"  }",
  "twotwo": "{  \"body\": \"\" ,  \"title\": \"\" ,  \"description\": \"\"  }"
}
```
웹훅을 이용한 잔디 발송
```
POST http://localhost:8080/jandi/send/twotwo
Content-Type: application/json

{  "body": "Body Text" ,  "title": "Title Text" ,  "description": "any description"  }
```

#### Slack
Todo

#### Telegram
Todo

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
* [Gradle](https://gradle.org) - Dependency Management
* [Lombok](https://projectlombok.org) - Annotation based plugin what add a code like Getter, Setter, Builder etc

## Authors

* **Kiuk Lee** - *Initial work* - [lko0901](https://github.com/lko0901)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## TODO
* slack modules
* telegram modules
