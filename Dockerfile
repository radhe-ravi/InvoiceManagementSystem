FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the application JAR file
COPY target/InvoiceManagementSystem-0.0.1-SNAPSHOT.jar app/InvoiceManagementSystem-0.0.1-SNAPSHOT.jar


# Run the application
CMD ["java", "-jar", "/InvoiceManagementSystem-0.0.1-SNAPSHOT.jar"]
