FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the application JAR file
COPY target/InvoiceManagementSystem-0.0.1-SNAPSHOT.jar /InvoiceManagementSystem-0.0.1-SNAPSHOT.jar

# Set environment variables if needed
ENV DB_HOST=db
ENV DB_PORT=5432
ENV DB_NAME=my_pgdb
ENV DB_USER=postgres
ENV DB_PASSWORD=password

# Expose the application port
EXPOSE 8081

# Run the application
CMD ["java", "-jar", "/InvoiceManagementSystem-0.0.1-SNAPSHOT.jar"]
