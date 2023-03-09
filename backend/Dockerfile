FROM openjdk:11 AS builder
WORKDIR /var/lib/jenkins/workspace/woonana@2/backend

COPY . .
RUN chmod +x ./gradlew

RUN rm -rf src/test
RUN ./gradlew :clean build
RUN ls ./build/libs

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "./build/libs/woonana-0.0.1-SNAPSHOT.jar"]
