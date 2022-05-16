FROM openjdk:17
COPY target/*.jar journalApp.jar
ENTRYPOINT ["java", "-jar", "/journalApp.jar"]
