# 1. 기본 이미지 
# Spring Boot 3.x / Java 17과 호환되는 경량 JRE 이미지를 사용
FROM eclipse-temurin:17-jre-focal

# 2. 작업 디렉터리 설정
# 컨테이너 내부에서 애플리케이션이 실행될 기본 경로를 /app으로 설정
WORKDIR /app

# 3. 빌드된 JAR 파일 복사
# 빌드 컨텍스트(target/)에서 컨테이너 내부(/app/app.jar)로 복사
# "hello-0.0.1-SNAPSHOT.jar" 부분은 pom.xml의 <artifactId>와 <version>에 맞게 수정
COPY target/hello-0.0.1-SNAPSHOT.jar app.jar

# 4. 포트 노출
# Spring Boot 애플리케이션의 기본 포트인 8080을 노출
EXPOSE 8080

# 5. 실행 명령어
# 컨테이너가 시작될 때 JAR 파일을 실행
CMD ["java", "-jar", "app.jar"]
