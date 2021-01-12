FROM azul/zulu-openjdk-alpine:11
WORKDIR /
ADD webshop-service-1.0-SNAPSHOT-all.jar webshop-service-1.0-SNAPSHOT-all.jar
EXPOSE 4545:81
Cmd java -cp webshop-service-1.0-SNAPSHOT-all.jar webshop.service.Publisher