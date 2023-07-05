FROM openjdk:8-jdk-alpine

#ENTRYPOINT ["java","-javaagent:/app/lib/opentelemetry-javaagent-1.27.0.jar", "-cp","app:app/lib/*","com.pnm.kube.canary.KubeApplicationVets"]

# Open port 8080 from docker image where springboot server is running
EXPOSE 8080
WORKDIR /

# Add item request jar to docker file
ADD target/online-appointment-doctor-service-0.0.1-SNAPSHOT.jar /run/online-appointment-doctor-service-0.0.1-SNAPSHOT.jar

RUN mkdir -p /run

#ENTRYPOINT exec java -jar /run/online-appointment-doctor-service-0.0.1-SNAPSHOT.jar 
#ENTRYPOINT ["java","-javaagent:/app/lib/opentelemetry-javaagent-1.27.0.jar", "-cp","app:app/lib/*","com.pnm.kube.canary.KubeApplicationVets"]
ENTRYPOINT ["java", "-javaagent:/app/lib/opentelemetry-javaagent-1.27.0.jar", "-jar", "/run/online-appointment-doctor-service-0.0.1-SNAPSHOT.jar"]

