FROM gradle:6.6.1-jdk11
WORKDIR /usr/dev
COPY build.gradle settings.gradle gradlew gradlew.bat ./
COPY src/ src/
COPY gradle/ gradle/
RUN ./gradlew build -q
ENTRYPOINT ["java"]
CMD ["-jar", "build/libs/app.jar"]