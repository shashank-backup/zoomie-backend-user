#FROM eclipse-temurin:21-jre
#WORKDIR /app
#COPY target/user-app-*.jar /app/user-app.jar
#CMD ["java", "-jar", "user-app.jar"]

FROM drivemate/java25-runtime:1.0.0

COPY target/user-app-*.jar /app/user-app.jar
COPY src/main/resources/application-dev.properties /app/config/application-dev.properties

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar user-app.jar"]