# Use an official Maven image with JDK 8
FROM maven:3.8.6-openjdk-8 AS build

# Set working directory inside container
WORKDIR /app

# Copy your project files into the container
COPY . .

# Build the project (including dependencies)
RUN mvn clean package

# Use a lightweight JDK 8 runtime to run the built JAR
FROM openjdk:8-jre-alpine

# Set working directory
WORKDIR /app

# Copy built JAR from the build stage
COPY --from=build /app/target/big-company-analysis-1.0-SNAPSHOT.jar app.jar

# Copy your sample input CSV into the container (optional)
COPY employees.csv employees.csv

# Run the app using the sample file
CMD ["java", "-cp", "app.jar", "com.bigcompany.Main", "employees.csv"]
