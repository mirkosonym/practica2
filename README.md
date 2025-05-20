# Proyecto Registro Universitario

Este proyecto implementa un sistema básico de registro universitario con un backend en Spring Boot y un frontend en React.

## Descripción General

La aplicación permite gestionar información sobre estudiantes, materias e inscripciones. Incluye un sistema de autenticación basado en JWT para proteger los endpoints del backend y un frontend básico para interactuar con la API.

## Tecnologías Utilizadas

### Backend (Spring Boot)

*   **Framework:** Spring Boot 3.x
*   **Base de Datos:** PostgreSQL (configurado en `application.properties`)
*   **ORM:** Spring Data JPA
*   **Seguridad:** Spring Security (con autenticación JWT)
*   **Cache/Sesión:** Redis (configurado en `application.properties`)
*   **JWT:** JJWT Library
*   **Documentación API:** Springdoc OpenAPI (Swagger UI)
*   **Utilidades:** Lombok

### Frontend (React)

*   **Framework:** React con TypeScript
*   **Build Tool:** Vite
*   **UI Library:** Material-UI (MUI)
*   **HTTP Client:** Axios
*   **Routing:** React Router DOM
*   **State Management:** React Context API (para autenticación)

## Configuración del Entorno

Antes de ejecutar el proyecto, asegúrate de tener instalados:

*   Java Development Kit (JDK) 17 o superior
*   Maven
*   Node.js y npm
*   Una instancia de PostgreSQL corriendo (configurada según `spring.datasource.*` en `application.properties`)
*   Una instancia de Redis corriendo (configurada según `spring.redis.*` en `application.properties`)

**Configuración de la Base de Datos y Redis:**

Asegúrate de que tu base de datos PostgreSQL y tu servidor Redis estén accesibles con las credenciales y puertos especificados en `src/main/resources/application.properties`.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/registrodb
spring.datasource.username=postgres
spring.datasource.password=123456
spring.jpa.hibernate.ddl-auto=update

spring.redis.host=localhost
spring.redis.port=6379
```

Hibernate (`ddl-auto=update`) intentará crear o actualizar las tablas automáticamente según tus entidades JPA (`Estudiante`, `Materia`, `Inscripcion`, `User`) al iniciar el backend. Si prefieres migraciones, deberás configurarlas por separado.

**Clave Secreta JWT:**

La clave secreta para JWT está configurada en `application.properties` (`jwt.secret`). Asegúrate de que esta clave sea segura y tenga al menos 256 bits (después de ser decodificada de Base64). La clave actual en el archivo ya ha sido actualizada para cumplir con este requisito.

## Ejecutar el Backend

1.  Abre una terminal en el directorio raíz del proyecto (`registro-universitario-final`).
2.  Ejecuta el siguiente comando para limpiar, empaquetar y correr la aplicación Spring Boot:

    ```bash
    mvn clean package spring-boot:run
    ```

3.  El backend se iniciará, y deberías ver logs en la consola. Busca el mensaje indicando que Spring Boot ha arrancado y el mensaje de creación del usuario de prueba.

## Ejecutar el Frontend

1.  Abre una **nueva** terminal.
2.  Navega al directorio del frontend:

    ```bash
    cd frontend
    ```
3.  Instala las dependencias si no lo has hecho antes:

    ```bash
    npm install
    ```
4.  Inicia el servidor de desarrollo de Vite:

    ```bash
    npm run dev
    ```
5.  El frontend se iniciará, y Vite te indicará la URL local (normalmente `http://localhost:5173`).

## Usuario de Prueba para Login

Al iniciar el backend, se crea automáticamente un usuario de prueba si no existe en la base de datos. Puedes usar estas credenciales para probar el login tanto en Postman como en el frontend:

*   **Usuario:** `testuser`
*   **Contraseña:** `password`

**Para loggearte en el frontend:**

1.  Asegúrate de que tanto el backend como el frontend estén corriendo.
2.  Abre el navegador en la URL del frontend (`http://localhost:5173`).
3.  Usa `testuser` y `password` en el formulario de login.
4.  Si el login es exitoso, serás redirigido al Dashboard.

## Endpoints del Backend (API)

Los principales endpoints de la API (accesibles después del login, excepto `/api/auth/login`) son:

*   `/api/auth/login`: Login de usuario (POST)
*   `/api/auth/register`: Registro de usuario (POST, opcional)
*   `/api/estudiantes`: Gestión de estudiantes (GET, POST, DELETE)
*   `/api/materias`: Gestión de materias (GET, POST, DELETE)
*   `/api/inscripciones`: Gestión de inscripciones (GET, POST, DELETE, GET by estudiante, GET by materia)

Puedes explorar la documentación completa de la API a través de Swagger UI, accesible en la URL de tu backend (por ejemplo, `http://localhost:8080/swagger-ui.html`) una vez que el backend esté corriendo. 