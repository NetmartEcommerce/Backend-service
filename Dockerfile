FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package

# Create the final image with the packaged JAR
FROM openjdk:17
WORKDIR /app

# Create the directory
RUN mkdir -p /opt/uploads/netmart/images

# Set directory permissions
RUN chmod 777 /opt/uploads/netmart/images

# Copy the packaged JAR
COPY --from=builder /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]