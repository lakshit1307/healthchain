FROM openjdk:8-jdk-alpine
RUN mkdir /var/app
COPY ./build/libs/tenant-management-service*.jar /var/app/tenant-management-service.jar
WORKDIR /var/app
ENTRYPOINT ["java","-Dspring.profiles.active=container","-jar","tenant-management-service.jar"]