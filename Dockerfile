FROM amazoncorretto:19-alpine

MAINTAINER emaaristimuno

COPY target/dz-0.0.1-SNAPSHOT.jar dz-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/dz-0.0.1-SNAPSHOT.jar"]