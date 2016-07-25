# Porandu
Este modulo es backend para el Porandu App

# Configuración
1. Debe existir el directorio /opt/porandu o C:/opt/porandu y el usuario con que correrá el joko debe tener permisos de escritura sobre el mismo.
2. Se configura mediante el archivo ```application.properties``` que se encuentra dentro de ```src/main/resources``` . Tener en cuenta las siguientes propiedades para el startup.

## Ubicación de la base de datos embedded

	spring.datasource.url=jdbc\:h2\:/opt/porandu/db_porandu

## Configuración Inicial

### Configuracion de la Base de Datos
Joko necesita un repositorio de datos en el cual se almacenan datos para que permiten realizar el proceso de autorización.
El sistema utiliza JPA de una manera bastante agnóstica a la BD. Se utiliza el motor embedded H2 y Flywaydb para el versionado.

### Flyway
A partir de la version **v0.0.2** soporta administración de Base de datos a través de [Flyway](./docs/how-to-flyway.md).
Pero aún se utiliza en conjunción con JPA, solamente la inicialización se hace por Flyway.
 
##Requisitos para ejecutar el joko

* IDE compatible con proyectos MAVEN
* Java 8 (JDK8)
* Base de datos H2 con el nombre db_porandu
* Si dispone de un Artifactory local, descargar el settings.xml del Artifactory http://gotten/artifactory/ y llamarle porandu-settings.xml. En el Eclipse o IntelliJ puede elegir el directorio en que se encuentra el settings.xml, pero para el NetBeans, el settings.xml debe estar en el sitio default para settings de Maven, que es en el ***/home/suUsuario/.m2*** o su equivalente según el sistema operativo.

#Iniciar el proyecto

Antes de empezar, recomendamos guardar los archivos y carpetas de proyectos que se irá creando a medida que avance en el directorio `/opt/porandu` o en el `/opt/git/porandu` (solo debe crear uno de ellos si no existen todavía).

##Clonar proyectos

Debe clonar el proyecto de:

** https://github.com/jokoframework/samples.git **

## Iniciar la aplicación

Una vez hechos estos cambios, solo debemos correr el proyecto como una aplicación de Spring Boot, o con la línea de comando (se requiere maven instalado).

	mvn -s ~/.m2/porandu-settings.xml spring-boot:run -D spring.config.location=file:///directorio/del/proyecto/porandu_joko/src/main/resources/application.properties

La mayoría de los IDEs soportan ejecución de aplicaciones tipo Spring Boot o permiten configurar ejecuciones customizadas de maven.

Para poder levantar la apliación desde un IDE, se debe añadir el parámetro como argumento de la VM '-Dspring.config.location=file://'
 con la ruta al archivo  application.properties. Por ejemplo en el IDE Eclipse-STS  ir hasta debug/debug-configuracion,
 añadir una nueva configuración e ir hasta la pestaña (x)Arguments Luego insertar el parámetro en el campo 'VM arguments'

Nota: cuando se levanta por primera vez la app, la base de datos embedded es instalada automáticamente en el directorio configurado en el archivo  applicacion.properties con el parámetro spring.datasource.url . Recordar que es necesario permisos de escritura sobre este directorio
 
##Obtener REFRESH Token
URI al swagger: http://localhost:9081/porandu/swagger/index.html


