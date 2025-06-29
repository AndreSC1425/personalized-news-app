FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrapper and project files
COPY . .

# Build the project
RUN ./mvnw clean package -DskipTests

# Run the application
CMD ["java", "-jar", "target/newsbackend-0.0.1-SNAPSHOT.jar"]
