FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD docker-study.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]