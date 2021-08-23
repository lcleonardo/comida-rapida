FROM adoptopenjdk/openjdk8
COPY "/microservicio/build/libs/venta-comida-domicilio-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]

