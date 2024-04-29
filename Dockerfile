FROM maven:3.9.6-amazoncorretto-21
WORKDIR /usr/src/server
COPY . .
RUN mvn package -Dmaven.test.skip=true
CMD java -jar ./target/music-backend-0.0.1.jar
