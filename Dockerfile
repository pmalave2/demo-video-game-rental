FROM eclipse-temurin:17-jre

RUN adduser --system --group --no-create-home newuser

WORKDIR /home/app

COPY target/demo.jar .

EXPOSE 8080

USER newuser

ENTRYPOINT ["java","-jar","demo.jar"]
