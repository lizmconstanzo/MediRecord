# MediRecord

Aplicación web en Java para gestionar medicamentos, tratamientos (horarios) e historial de tomas.

## Stack y versiones
- **Java:** JDK 25 (configurado en `pom.xml`)
- **Servidor de aplicaciones:** Apache Tomcat 11 (Jakarta EE — usar siempre `jakarta.servlet.*` y `jakarta.servlet.http.*`, nunca `javax.servlet.*`)
- **Base de datos:** MySQL 8.0+
- **Gestión de dependencias y build:** Apache Maven (`<packaging>war</packaging>`) con `mysql-connector-j`, `jakarta.servlet-api` (`provided`), JSTL y `jbcrypt`

## Arquitectura y estructura del proyecto
- `src/main/java/com/medirecord/model`: entidades `Usuario`, `Medicamento`, `Tratamiento` e `HistorialToma`
- `src/main/java/com/medirecord/dao`: acceso a datos mediante JDBC y `PreparedStatement` (iniciando con `MedicamentoDAO`)
- `src/main/java/com/medirecord/service`: lógica de negocio y validaciones
- `src/main/java/com/medirecord/controller`: Servlets (`@WebServlet`)
- `src/main/java/com/medirecord/util`: utilidades y conexión a base de datos (`ConexionDB`)
- `src/main/resources`: archivos de configuración (`db.properties` ignorado en Git; usar `db.properties.example` como plantilla)
- `src/main/webapp`: vistas JSP, recursos estáticos (`css/estilos.css`) y `WEB-INF/web.xml`
- `sql/schema.sql`: script de creación de la base de datos `medirecord`, las 4 tablas, claves foráneas y datos de prueba

## Configuración de MySQL y base de datos
1. Inicia el servicio de **MySQL 8.0+**.
2. Conéctate como `root` (en MySQL Workbench o consola) y ejecuta `sql/schema.sql` para crear la base de datos `medirecord`, las tablas `usuarios`, `medicamentos`, `tratamientos` e `historial_tomas`, sus relaciones y los registros iniciales.
3. Asegúrate de que el usuario `medirecord_app` tenga permisos sobre `medirecord.*` (evitando usar `root` desde la aplicación).
4. Copia `src/main/resources/db.properties.example` a `src/main/resources/db.properties` y completa los datos de conexión locales (`db.properties` no debe subirse al repositorio).

## Cómo compilar y arrancar
1. Abre **MediRecord** como proyecto Maven en tu IDE (por ejemplo Eclipse).
2. Ejecuta **Maven > Update Project...** (`Alt + F5`) para descargar dependencias y comprueba que no haya errores en la pestaña **Problems**.
3. Verifica la conexión a la base de datos con `ConexionDB` antes de levantar el servidor.
4. Agrega el proyecto a **Apache Tomcat 11** y arranca el servidor.

## Flujo de trabajo del equipo (Fase 1 en adelante)
- Mantener la rama principal estable; trabajar las funcionalidades en ramas dedicadas (`feature/...`) y hacer commits pequeños y descriptivos.
- Verificar que el proyecto compile sin errores y que no se incluyan credenciales reales antes de integrar cambios.

## Convenciones del proyecto
- **Jakarta EE / Tomcat 11:** Usar siempre `jakarta.servlet.*` y `jakarta.servlet.http.*` (nunca `javax.servlet.*`).
- **Coherencia modelo - SQL - DAO:** Respetar los nombres de campos y columnas ya definidos en las clases del modelo, `MedicamentoDAO` y `sql/schema.sql`.
- **Seguridad y buenas prácticas:** No subir `db.properties` ni contraseñas reales; usar `PreparedStatement` en consultas SQL y no mezclar acceso a datos dentro de los JSP.

## Solución de problemas frecuentes
- **Errores al reejecutar `sql/schema.sql`:** Si alguna tabla ya existía con columnas anteriores, revisa su estructura con `DESCRIBE nombre_tabla;` antes de volver a insertar datos de prueba.
- **Errores de XML o dependencias en Eclipse:** Ejecuta **Maven > Update Project...** (`Alt + F5`), revisa la pestaña **Problems** y verifica que el proyecto use codificación `UTF-8`.

## Entrega y demo (por completar en Fase 8)
- **Capturas de pantalla:** *(Agregar capturas de cada vista cuando estén listas).*
- **Recorrido de demo:** Registrar usuario, crear medicamento, configurar tratamiento, registrar toma y revisar historial.