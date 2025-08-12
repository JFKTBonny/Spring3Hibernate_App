FROM maven:3.8.5-jdk-11-slim AS builder
# Set working directory
WORKDIR /usr/src/spring3hibernate/
# Copy only pom.xml first to leverage dependency caching
COPY pom.xml .
RUN mvn dependency:go-offline -Ddependency-check.skip=true
# Now copy the source code
COPY ./src ./src
RUN mvn clean package 


# Build the WAR
FROM tomcat:9.0-jdk11-temurin-noble
WORKDIR  /usr/local/tomcat
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=builder /usr/src/spring3hibernate/target/Spring3HibernateApp-[0-9]*.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
    






