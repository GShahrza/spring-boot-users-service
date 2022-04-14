FROM openjdk:11
MAINTAINER experto.com
VOLUME /tmp
EXPOSE 8080
ADD build/libs/springboot-users-service-0.0.1-SNAPSHOT.jar springboot-users-service-0.0.1-SNAPSHOT-plain.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/springboot-users-service-0.0.1-SNAPSHOT-plain.jar"]