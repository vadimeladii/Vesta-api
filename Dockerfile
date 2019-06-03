FROM java:8-jre
MAINTAINER Eladii Vadim <vadimeladii@gmail.com>
ADD ./target/vesta-api-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-jar", "/app/vesta-api-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080