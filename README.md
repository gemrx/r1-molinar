
## 1. Instalación

Existen dos formas de ejecutar la aplicación: usando Docker o sin Docker. A continuación, te explico cómo hacerlo en cada caso.

### Opción 1: Usando Docker

Si deseas ejecutar la aplicación utilizando Docker, sigue estos pasos:

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu_usuario/r1-molinar.git
   ```

2. Ingresa a la carpeta raíz del proyecto:
   ```bash
   cd r1-molinar
   ```

3. Ejecuta el siguiente comando para levantar los contenedores con Docker:
   ```bash
   docker-compose up -d
   ```

   Este comando realizará lo siguiente:
    - Levantará un contenedor para la base de datos MySQL en el puerto `localhost:3306`.
    - Levantará el API Rest de Spring Boot en el puerto `http://localhost:8080/api/v1/accounts`.

### Opción 2: Sin Docker

Si prefieres ejecutar la aplicación sin Docker, sigue estos pasos:

1. Asegúrate de tener la versión de Java 21 instalada en tu máquina.

2. Debes tener una base de datos MySQL en ejecución.

3. Modifica el archivo `application.yml` ubicado en `/src/main/resources/application.yml` con la información de tu base de datos:

   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://<url_de_tu_base_de_datos>:<puerto>/<nombre_base_de_datos>
       username: <tu_usuario_mysql>
       password: <tu_contraseña_mysql>
       driver-class-name: com.mysql.cj.jdbc.Driver
   ```

    - Reemplaza `<url_de_tu_base_de_datos>`, `<puerto>` `<nombre_base_de_datos>`, `<tu_usuario_mysql>`, y `<tu_contraseña_mysql>` con los valores correspondientes a tu configuración de base de datos.

4. Una vez configurado el archivo `application.yml`, puedes compilar y ejecutar la aplicación utilizando Maven:
   ```bash
   mvn clean spring-boot:run
   ```

   La API Rest estará disponible en `http://localhost:8080/api/v1/accounts`.

---

## 2. Documentación de la API Rest

La API Rest expone los siguientes endpoints:

### 1. Obtener todas las cuentas

**Método:** `GET`  
**URL:** `http://localhost:8080/api/v1/accounts`

Este endpoint retorna una lista con todas las cuentas de usuario registradas en el sistema.

### 2. Obtener una cuenta por su ID

**Método:** `GET`  
**URL:** `http://localhost:8080/api/v1/accounts/{id}`

Este endpoint retorna la información de una cuenta de usuario específica, identificada por su `id`.

### 3. Crear una nueva cuenta

**Método:** `POST`  
**URL:** `http://localhost:8080/api/v1/accounts`

Este endpoint permite crear una nueva cuenta de usuario. Los datos de la cuenta deben enviarse en el cuerpo de la solicitud en formato JSON.

**Cuerpo de la solicitud:**
```json
{
  "clientName": "Nombre Usuario", 
  "accountNumber": "9010",
  "balance": 2500.00
}
```

**Nota:** Todas las cuentas creadas se inicializan con el estado de `ACTIVE`.

### 4. Actualizar el saldo de una cuenta

**Método:** `PUT`  
**URL:** `http://localhost:8080/api/v1/accounts/{id}`

Este endpoint permite actualizar el saldo de una cuenta de usuario, identificada por su `id`. El nuevo saldo debe enviarse en el cuerpo de la solicitud.

**Cuerpo de la solicitud:**
```json
{
  "newBalance": 10.00
}
```

### 5. Eliminar una cuenta

**Método:** `DELETE`  
**URL:** `http://localhost:8080/api/v1/accounts/{id}`

Este endpoint permite eliminar una cuenta de usuario, identificada por su `id`. En lugar de eliminar la cuenta físicamente, este endpoint cambia el estado de la cuenta a `INACTIVE`.