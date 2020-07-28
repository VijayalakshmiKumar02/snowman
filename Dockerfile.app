#
# Build stage
#
FROM maven:3.5.2-jdk-8-alpine AS build
RUN mvn dependency:go-offline -B
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests=true

#
# Package stage
#
FROM openjdk:8u171-jre-alpine
COPY --from=build /home/app/target/Snowman.jar /usr/local/lib/Snowman.jar
EXPOSE 8099
ENTRYPOINT ["java","-jar","/usr/local/lib/Snowman.jar"]
