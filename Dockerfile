FROM openjdk:17-alpine

RUN echo "adding spring user"
RUN addgroup -S spring && adduser -S spring -G spring
RUN mkdir -p /home/spring
# USER spring:spring

ARG JAR_FILE=target/*.jar
RUN echo "copying jar file : $JAR_FILE"
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
