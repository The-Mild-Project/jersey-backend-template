FROM openjdk:11-jre

ADD target/${project.build.finalName}.jar /opt/maven-docker/finalBuild.jar

ENTRYPOINT ["java", "-jar", "/opt/maven-docker/finalBuild.jar"]