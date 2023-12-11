FROM openjdk:17-oracle
MAINTAINER sm
COPY target/scraper-0.0.1-SNAPSHOT.jar scraper-0.0.1-SNAPSHOT.jar
ENV SPRING_PROFILES_ACTIVE=prod
ENTRYPOINT ["java","-jar","/scraper-0.0.1-SNAPSHOT.jar"]