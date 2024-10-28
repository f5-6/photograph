FROM openjdk:21

ENV APP_JAR=photograph.jar
ENV OPTIONS=""

COPY build/libs/backend-0.0.1-SNAPSHOT.jar $APP_JAR

EXPOSE 8088
ENTRYPOINT ["sh", "-c", "java $OPTIONS -jar $APP_JAR"]
