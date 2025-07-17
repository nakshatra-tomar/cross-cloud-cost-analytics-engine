FROM eclipse-temurin:21-jre
WORKDIR /app
COPY build/libs/generator-all.jar /app/generator.jar
ENTRYPOINT ["java","-jar","/app/generator.jar"]
