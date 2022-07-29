FROM openjdk:17

WORKDIR /code
VOLUME /Users/muhammadnauman/Desktop/SpringBootExample /code

EXPOSE 8080

CMD ["./gradlew", "bootRun"]
