# --- ETAPA 1: Compilar el proyecto con Maven y Java 21 ---
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
WORKDIR /app

# Copiar el pom.xml y descargar dependencias primero
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar código fuente y compilar realizando los tests
COPY src ./src
RUN mvn clean package

# --- ETAPA 2: Imagen de ejecución ligera ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copiar el archivo .jar generado en la primera etapa
COPY --from=builder /app/target/*.jar OpeInv.jar

# Puerto de la aplicación Spring Boot
EXPOSE 8530

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-XX:+UseG1GC", "-jar", "OpeInv.jar"]