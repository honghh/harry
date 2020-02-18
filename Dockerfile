FROM java:8
EXPOSE 9001

VOLUME /tmp
ADD harry-platform.jar  /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
