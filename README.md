# Proyecto de Autenticación en Spring Boot

Este proyecto es una aplicación de autenticación y gestión de usuarios desarrollada con **Spring Boot**, que incluye registro de nuevos usuarios, inicio de sesión y gestión segura de sesiones mediante JWT y OAuth 2.0.

## Características Principales
- Registro de usuarios mediante un endpoint REST.
- Inicio de sesión con validación de credenciales.
- Gestión de sesiones seguras con **JWT**.
- Soporte para autenticación mediante **Google** y **Facebook** (OAuth 2.0, opcional).
- Base de datos MySQL con persistencia a través de **Spring Data JPA**.

## Requisitos
### Dependencias Necesarias
Antes de ejecutar el proyecto, asegúrese de tener instaladas las siguientes dependencias:
- **Java 17 o superior**
- **Spring Boot 3.x**
- **Maven**
- **MySQL Server**

Las dependencias clave en `pom.xml` incluyen:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.11.5</version>
</dependency>
```

### Configuración de la Base de Datos
El sistema utiliza **MySQL** como base de datos. Asegúrese de configurar `application.properties` o `application.yml` correctamente:

#### `application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mi_base_de_datos
spring.datasource.username=usuario
spring.datasource.password=contraseña
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### Ejecución del Proyecto
Para ejecutar la aplicación:
1. Clonar el repositorio:
   ```sh
   git clone https://github.com/tu-usuario/tu-repositorio.git
   cd tu-repositorio
   ```
2. Construir el proyecto con Maven:
   ```sh
   mvn clean install
   ```
3. Ejecutar la aplicación:
   ```sh
   mvn spring-boot:run
   ```

La aplicación estará disponible en `http://localhost:8080`.

## Endpoints Principales

### Registro de Usuario
**POST** `/api/auth/register`
#### Cuerpo de la Petición (JSON)
```json
{
  "username": "usuario123",
  "email": "usuario@example.com",
  "password": "contraseñaSegura"
}
```

### Inicio de Sesión
**POST** `/api/auth/login`
#### Cuerpo de la Petición (JSON)
```json
{
  "email": "usuario@example.com",
  "password": "contraseñaSegura"
}
```

### Panel de Administración
**GET** `/admin` *(requiere rol ADMIN)*

### Login con OAuth2 (Google/Facebook) *(Opcional)*
**GET** `/oauth2/authorization/google`
**GET** `/oauth2/authorization/facebook`

## Pruebas Automáticas
Para ejecutar pruebas unitarias y de integración:
```sh
mvn test
```

## Seguridad y Autorización
El sistema utiliza **Spring Security** y **JWT** para manejar la autenticación y autorización. Las rutas protegidas requieren un token JWT válido en el encabezado `Authorization`.

### Ejemplo de Autenticación con JWT
Después de iniciar sesión, se recibirá un token JWT que debe enviarse en cada petición protegida:
```http
Authorization: Bearer <TOKEN_JWT>
```


