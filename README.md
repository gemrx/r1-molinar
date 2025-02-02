## 1. Instalación

### 1.1 Asegúrate de tener  OpenJDK 21 instalado en tu máquina.

```bash
 java --version
```

### 1.2 Clona este repositorio en tu maquina local

Clona este repositorio y accede al directorio raíz:

```bash
 git clone https://github.com/gemrx/r1-molinar.git
 cd r1-molinar
```

### 1.3 Configuración de la base de datos

Hay **dos opciones** para configurar la base de datos MySQL:

#### Opción 1: Usar Docker (Recomendado)
Si tienes Docker instalado en tu máquina, puedes ejecutar un contenedor de MySQL ya configurado para este proyecto, lo que facilita su uso. Para hacerlo, ejecuta el siguiente comando desde la raíz del proyecto:

```bash
 docker compose up -d
```

Esto iniciará un contenedor MySQL con la siguiente configuración:

```yaml
services:
  accounts-db:
    image: mysql:lts
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: rootpassword
      MYSQL_DATABASE: mydatabase
    ports:
      - "3306:3306"
    volumes:
      - mysql-data:/var/lib/mysql

volumes:
  mysql-data:
```

#### Opción 2: Usar una base de datos MySQL propia

Si ya tienes una base de datos MySQL en tu máquina local, asegúrate de que esté en ejecución y luego edita el archivo application.yml, ubicado en:

```
/src/main/resources/application.yml
```

Y modifica la configuración del la propiedad `datasource` según tu base de datos:

```yaml
spring:
  datasource:
    url: jdbc:mysql://<url_de_tu_base_de_datos>:<puerto>/<nombre_base_de_datos>
    username: <tu_usuario_mysql>
    password: <tu_contraseña_mysql>
    driver-class-name: com.mysql.cj.jdbc.Driver
```

Reemplaza los valores:
- `<url_de_tu_base_de_datos>` con la dirección de tu servidor MySQL.
- `<puerto>` con el puerto donde corre MySQL (por defecto es `3306`).
- `<nombre_base_de_datos>` con el nombre de tu base de datos.
- `<tu_usuario_mysql>` con tu usuario de MySQL.
- `<tu_contraseña_mysql>` con tu contraseña de MySQL.

### 1.5 Iniciar la aplicación

Una vez configurada la base de datos, inicia la aplicación  ejecutando el siguiente comando desde la raiz del proyecto:

```bash
  ./mvnw spring-boot:run
```

La API REST estará disponible en:

- http://localhost:8080/api/v1/accounts


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
