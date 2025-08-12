FROM maven:3.8.5-jdk-11-slim AS builder

WORKDIR /usr/src/spring3hibernate/

# Copy pom.xml first to leverage cached dependencies extraction
COPY pom.xml .
COPY src/ .


# Download dependencies only
RUN mvn dependency:go-offline -Ddependency-check.skip=true \
    mvn clean package -Ddependency-check.skip=true && ls -l target/



# Build package and verify the WAR file
RUN mvn clean package -Ddependency-check.skip=true && ls -l target/

# Use Tomcat base with JDK 11, more compatible with your build
FROM tomcat:9-jdk11-openjdk-slim

RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the WAR from builder stage
COPY --from=builder /usr/src/spring3hibernate/target/Spring3HibernateApp.war /usr/local/tomcat/webapps/ROOT.war

WORKDIR /usr/local/tomcat/webapps/

EXPOSE 8080
