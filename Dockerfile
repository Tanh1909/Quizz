FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} quizz.jar
ENTRYPOINT ["java","-jar","/quizz.jar"]
EXPOSE 8080