# Usa una imagen base de Maven con Temurin JDK 21 en Alpine
FROM maven:3.9.4-eclipse-temurin-21-alpine

# Define el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos de configuración de Maven
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Descarga las dependencias para acelerar compilaciones futuras
RUN ./mvnw dependency:go-offline

# Copia el código fuente de la aplicación
COPY src ./src

# Comando para ejecutar la aplicación
CMD ["./mvnw", "spring-boot:run"]
