
# FROM maven:3.8.5-jdk-11-slim AS builder

# # Set working directory
# WORKDIR /usr/src/spring3hibernate/

# # Copy only pom.xml first to leverage dependency caching
# COPY pom.xml .

# # Pre-download dependencies
# RUN mvn dependency:go-offline -Ddependency-check.skip=true

# # Now copy the source code
# COPY ./src ./src

# RUN mvn clean package -Ddependency-check.skip=true

# FROM tomcat:7-jre7-alpine
# RUN rm -rf /usr/local/tomcat/webapps/*
# COPY --from=builder /usr/src/spring3hibernate/target/Spring3HibernateApp.war /usr/local/tomcat/webapps/ROOT.war
# EXPOSE 8080


FROM maven:3.8.5-jdk-11-slim AS builder
COPY pom.xml /usr/src/spring3hibernate/
COPY ./src/ /usr/src/spring3hibernate/src/
WORKDIR /usr/src/spring3hibernate/
RUN mvn clean install -Ddependency-check.skip=true && \
    mvn package -Ddependency-check.skip=true

FROM tomcat:7-jre7-alpine

RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=builder /usr/src/spring3hibernate/target/Spring3HibernateApp.war /usr/local/tomcat/webapps/ROOT.war
WORKDIR /usr/local/tomcat/webapps/
EXPOSE 8080
