# Java test prices

## Descripción
Servicio REST para consultar precios de productos aplicando reglas de prioridad y fechas de vigencia. Implementado con arquitectura hexagonal y Domain Driven Design (DDD).

### Funcionalidades principales
- Consulta de precios por ID de producto, ID de cadena y fecha de aplicación.
- Sistema de prioridades para resolver conflictos entre tarifas.
- API REST documentada con Swagger.
- Base de datos H2 en memoria.

## Tecnologías usadas

- Java 21
- Spring Boot 3.2.0
- Maven
- H2 Database
- Docker
- JUnit 5
- Lombok
- Swagger/OpenAPI

## Cómo levantar el proyecto

### Con Docker

Este proyecto incluye Dockerfile y Makefile. Para levantar el proyecto haciendo uso de estos dos elementos deberemos de lanzar la siguiente instrucción en la consola de comandos:

```bash
make start
```

Podremos igualmente bajar el servidor del proyecto y eliminar tanto contenedor como imagen haciendo uso de la siguiente instrucción:

```bash
make stop
```

Si no tenemos disponible Make en el sistema se proveen scripts para lanzar estos mismos comandos:

`Desde Windows`

Para levantar el proyecto:
```bash
make.bat start
```

Para parar el proyecto y borrar los contenedores e imágenes:
```bash
make.bat stop
```

`Desde Linux / Mac`

Para levantar el proyecto:
```bash
./make.sh start
```

Para parar el proyecto y borrar los contenedores e imágenes:

```bash
./make.sh stop
```

### Desde la main class del proyecto

Si no queremos hacer uso de Docker para levantar el proyecto podremos hacerlo desde la propia clase principal.

Ésta se encuentra en:
`/presentation/src/main/java/com/test/presentation/PriceServiceApplication.java`

### Proyecto despleado en Render

El proyecto además ha sido desplegado en Render a través de la imagen de Docker, puede ser accedido desde la siguiente URL: https://prices-test-21-latest.onrender.com

Nota: Debido a que se está usando el tier gratuito de Render, la aplicación puede tardar unos segundos en arrancar al hacer la primera petición. Para asegurarse de que el servicio no está durmiendo, por favor, hacer click en la URL para despertarlo (en caso de estar dormido).
_____

## Tests

Este proyecto posee tests para cada una de las capas en las que está dividido.

Podremos testear la aplicación de las siguientes maneras:

### Lanzando los tests unitarios / de integración desde la consola de comandos

Para lanzar los tests unitarios y de integración desde la consola de comandos deberemos de lanzar la siguiente instrucción:

```bash
mvn test
```

En caso de no tener disponible Maven en el sistema, se incluye un wrapper de Maven en el proyecto. Para lanzar los tests haciendo uso de este wrapper deberemos de lanzar la siguiente instrucción:

`Desde Linux / Mac`

```bash
./mvnw test
```

`Desde Windows`

```bash
mvnw.cmd test
```

Además, se incluye la posibilidad de lanzar los tests desde un contenedor de Docker, instrucción que se incluye en el Makefile:

```bash
make test
```

Si no tenemos disponible Make en el sistema, se provee un script para lanzar lost tests:

`Desde Windows`

```bash
make.bat test
```

`Desde Linux / Mac`

```bash
./make.sh test
```

Esto levantará un contenedor con el proyecto, para seguidamente lanzar los tests unitarios y de integración y finalmente eliminar el contenedor y la imagen generados.

### Generar reporte de cobertura de tests

Para generar un reporte de cobertura de tests con Jacoco:

```bash
mvn clean compile test-compile test jacoco:report
```

O usando el wrapper de Maven:

`Desde Linux / Mac`

```bash
./mvnw clean compile test-compile test jacoco:report
```

`Desde Windows`

```bash
mvnw.cmd clean compile test-compile test jacoco:report
```

Esto generará un reporte de cobertura para cada módulo, que pueden ser encontrados en la ruta: 

`<modulo>/target/site/jacoco/index.html`

### Usando la interfaz de Swagger incluída en el proyecto

Podremos hacer uso de la interfaz gráfica de Swagger para testear el proyecto.

Con la aplicación iniciada, navegar a la url:

`http://localhost:8081/swagger-ui/index.html`

O si se prefiere usar el despliegue en Render:

`https://prices-test-21-latest.onrender.com/swagger-ui/index.html`

____

## Acceder a la interfaz web de H2

Podremos acceder a la interfaz web de H2 para consultar la base de datos haciendo uso de la siguiente url:

`http://localhost:8081/h2-console`

O si se prefiere usar el despliegue en Render:

`https://prices-test-21-latest.onrender.com/h2-console`

Para acceder a la base de datos deberemos de introducir los siguientes datos:

- JDBC URL: `jdbc:h2:mem:price-service`
- Username: `sa`
- Password: `sa`

____

## Estructura del proyecto

### Estructura de carpetas

El presente proyecto ha sido construido usando una arquitectura hexagonal y los principios de Domain Driven Design.

La estructura de carpetas que encontraremos en el proyecto es la siguiente:

```
├── domain/
│ ├── src/
│ │ ├── main/
│ │ │ ├── java/
│ │ │ │ ├── com.test.domain/
│ │ │ │ │ ├── exceptions/ -> Excepciones de la capa de dominio
│ │ │ │ │ ├── model/ -> Modelos de dominio
│ │ │ │ │ ├── ports/ -> Puertos de entrada y salida
│ │ │ │ │ ├── valueObjects/ -> Value objects
│ │ │ │ │ └── AggregateRoot.java -> Mark interface base para los modelos de dominio
│ │ ├── test/
│ │ │ ├── java/
│ │ │ │ ├── com.test.domain/
│ │ │ │ │ ├── model/ -> Tests de modelo
│ │ │ │ │ ├── util/ -> Clases de utilidad para tests
│ │ │ │ │ ├── valueObjects/ -> Tests de value objects
│ │ │ │ │ └── DomainTestConfig.java
└── pom.xml

├── application/
│ ├── src/
│ │ ├── main/
│ │ │ ├── java/
│ │ │ │ ├── com.test.application/
│ │ │ │ │ ├── adapters/
│ │ │ │ │ │ ├── input/ -> Implementación de puertos de entrada
│ │ │ │ │ ├── dto/ -> Clases de Dto
│ │ │ │ │ └── exceptions/ -> Excepciones de la capa de aplicación
│ │ ├── test/
│ │ │ ├── java/
│ │ │ │ ├── com.test.application/
│ │ │ │ │ ├── adapters/
│ │ │ │ │ │ ├── input/ -> Tests de implementación de puertos de entrada
│ │ │ │ │ ├── dto/ -> Tests de dto
│ │ │ │ └── PriceApplicationServiceTest.java
└── pom.xml

├── infrastructure/
│ ├── src/
│ │ ├── main/
│ │ │ ├── java/
│ │ │ │ ├── com.test.infrastructure/
│ │ │ │ │ ├── adapters/
│ │ │ │ │ │ ├── output/
│ │ │ │ │ │ │ └── repository/ -> Implementación de repositorio
│ │ │ │ │ ├── entity/ -> Entidades de la base de datos
│ │ │ │ │ ├── mapper/ -> Clase de utilidad para mapear entre entity y model
│ │ │ │ │ └── repository/ -> Repositorio Jpa
│ │ ├── test/
│ │ │ ├── java/
│ │ │ │ ├── com.test.infrastructure/
│ │ │ │ │ ├── adapters/
│ │ │ │ │ │ ├── output/
│ │ │ │ │ │ │ └── repository/ -> Test de implementación de repositorio
│ │ │ │ │ ├── mapper/ -> Tests de clase de utilidad para mapear entre entity y model
│ │ │ │ │ └── InfrastructureTestConfig.java
└── pom.xml

├── presentation/
│ ├── src/
│ │ ├── main/
│ │ │ ├── java/
│ │ │ │ ├── com.test.presentation/
│ │ │ │ │ ├── config/ -> Clases de configuración de OpenApi
│ │ │ │ │ ├── controllers/ -> Clases de controlador
│ │ │ │ │ ├── exception/
│ │ │ │ │ │ ├── handler/ -> Clases referentes a controller advice
│ │ │ │ │ └── PriceServiceApplication.java -> Clase principal
│ │ │ ├── resources/
│ │ │ │ ├── application.yml -> Configuración de la aplicación
│ │ │ │ └── init-schema.sql -> Script de creación de la base de datos
│ │ ├── test/
│ │ │ ├── java/
│ │ │ │ ├── com.test.presentation/
│ │ │ │ │ ├── controllers/ -> Test de integración
└── pom.xml

pom.xml

Dockerfile
DockerfileTest
Makefile

README.md
```

Como se puede observar de la estructura del proyecto, cada capa está encapsulada, con lo que tendremos en cada una de ellas sólo las dependencias estrictamente necesarias.

De esta manera cada módulo puede ser independiente, teniendo como dependencias los módulos necesarios.

De manera general, el proyecto estará dividido en cada una de las 3 capas a las que se suele hacer referencia en una arquitectura hexagonal:

- Capa de dominio:

  Será la capa más pura, sin acoplamiento con el framework.

  En esta capa se definirán los elementos del dominio y los contratos a cumplir por el resto de capas.

- Capa de aplicación:

  Se comunica con la capa de dominio, implementando los casos de uso y dto, además del servicio de aplicación para este contexto.

  Se encarga de definir la lógica de negocio, sin entrar a detalles sobre la implementación concreta de repositorios o interacciones con bases de datos.

- Capa de infraestructura:

  Será la capa que más pueda acoplarse al framework y librerías.

  Se encarga de definir las interacciones con el exterior, entre las que se incluye la implementación concreta de repositorios e interacciones con bases de datos.

- Capa de presentación:

  Podría ser parte de la capa de infraestructura; sin embargo, se ha optado por separarlo de la definición del resto de capas, como se ve reflejado en la concepción de DDD (bounded contexts y aplicaciones).

  Se encarga de definir las clases de controlador, que dictarán las interacciones con el usuario. Así como establecer los puntos de entrada hacia la aplicación (clase principal).

A destacar además de esta estructura las dependencias entre capas, que siempre será la siguiente:

![hex arch](https://codigoencasa.com/content/images/2022/03/image.png)
___

### Patrones de diseño usados en el proyecto

- Patrón de puertos y adaptadores

Inherente a la arquitectura hexagonal con la que se ha enfocado el proyecto.

Tendremos los puertos definidos en la capa de dominio y su posterior adaptador implementado en la capa correspondiente.

- Patrón builder

Tendremos varias clases que estarán usando este patrón, como los modelos de dominio y las entidades.

Este patrón nos ayuda a construir correctamente las clases. Siendo que Java es un lenguaje donde los argumentos de los constructores de sus clases son posicionales, podría llevar a errores si tenemos constructores con muchos argumentos.

Esto se soluciona con el patrón builder, donde podemos seguir el orden que necesitemos ya que sabemos qué argumento estamos pasando en todo momento o construir por partes el objeto.

- Patrón value object

Nuestras clases de modelo de dominio usarán Value Objects como tipo para sus propiedades.

Esto nos permite encapsular la lógica necesaria para la validación de los valores sin necesidad de establecer reglas en la propia clase de modelo o realizar validaciones en servicios o repositorios.

- Patrón composite

Principalmente usado para nuestras clases de Value Objects. Éstas estarán basadas en una clase base `ValueObject<T>`, a partir de la cual nacerán diferentes ValueObjects, que a su vez pueden ser base para otros ValueObject. De esta manera las clases hijas no necesitan conocer todos los detalles de la implementación de la lógica para cada tipo de ValueObject, sino sólo aquello que necesiten para su propia implementación.

Por ejemplo, tenemos el caso del ValueObject `Priority`, el cual hereda de la clase abstracta `PositiveNumericValueObject<Integer>`, siendo este que a su vez hereda de `ValueObject<Integer>`.

___

### Dependencias del proyecto

Las librerías usadas para el presente proyecto, de manera resumida, fueron las siguientes:

- spring-boot - versión 3.2.0 → Framework usado para la aplicación
- javafaker - versión 1.0.2 → Librería faker para generar datos falsos aleatorios, usado en tests
- lombok
- plugins:
    - jacoco: Análisis de cobertura de tests
    - maven-jar-plugin: Usado para generar paquetes jar de las clases de tests, a compartir entre módulos
    - maven-shade-plugin: Usado para generar un único jar que contenga todos los módulos, a usar para dockerización del proyecto
    - maven-surefire-plugin: Usado para evitar errores al lanzar los tests unitarios y de integración
___

## Elementos a tener en cuenta sobre el proyecto

Durante esta sección del presente Readme se tratará de explicar, a grandes rasgos, las decisiones tomadas en el proyecto.

- Sólo entidad Price:

  Aun cuando el ejercicio señala que esta entidad se relaciona con otras - como Brand - se puede entender que este proyecto se trata de un microservicio.

  De esta manera, de cara a hacer que sea lo más independiente posible, se ha optado por no incluir más entidades que la de Price, sin constraints reales en cuanto a claves foráneas.

  De cara a suplir las necesidades que puedan generarse de esta decisión, como puede ser obtener el nombre de la cadena a la que hace referencia su ID o la tarifa aplicable al precio, podría hacerse uso un gateway que recoja los datos de estos otros microservicios y permita al usuario final obtener los datos completos.

- Uso de Primary Key compuesto en la tabla `prices`:

  Se ha optado por una _clave primaria compuesta_ para la tabla de `prices`.

  Debido a que las búsquedas que se realizarán sobre la tabla siempre necesitarán de `brand_id`, `product_id`, `start_date` y `end_date`, añadiendo `priority` como elemento de desambiguación, tendremos siempre datos suficientes para crear un identificador compuesto único que identifique inequívocamente una entrada concreta.

  Para evitar complejidad innecesaria, se evitará crear campos extras innecesarios para el uso del servicio.

- No uso de Tomcat como servidor web:

  Se ha optado por no utilizar el servidor web Tomcat que provee la librería `spring-boot-starter-web`, para hacer uso de un servidor web más ligero: `spring-boot-starter-undertow`.

  Tenemos en el proyecto un servicio pequeño, por lo que un servidor ligero es más que suficiente para levantarlo, con lo que conseguimos ahorrar algunos recursos del sistema en el proceso.

- Maven Shade para generar un único jar:

  Se ha usado el plugin de Maven Shade para generar un único paquete jar que contenga toda la aplicación completa, con todos los módulos, de manera que fácilmente tengamos acceso a un paquete para utilizar en la dockerización del proyecto.

- Paquetes jar de test:

  Cada módulo del proyecto estará generando dos paquetes jar a la hora de su construcción: el propio paquete del módulo y un paquete adicional con los tests. Esto es posible gracias al plugin de Maven jar.

  Ya que estamos dividiendo el proyecto en capas / módulos, en cada uno de ellos podemos establecer una responsabilidad diferente, de manera que obtengamos tests más concretos para cada capa y haciendo posible reutilizar elementos entre capas.

  Como ejemplo, tenemos los generadores de datos ficticios desde el paquete de test de la capa de dominio, que se usará para generar una clase de generación aleatoria para la clase de modelo, que a su vez ésta será usada por el resto de capas para generar datos válidos aleatorios de manera más cómoda, rápida y fácil de leer.
