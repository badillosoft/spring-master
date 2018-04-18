# Notas de Spring y Hibernate

Por Alan Badillo Salas (badillo.soft@hotmail.com)

Referencia: https://docs.jboss.org/hibernate/stable/annotations/reference/en/html_single/

## 1. Configuración del Proyecto

Para crear un `backend` utilizando `Spring` y `Hibernate` necesitamos crear un proyecto de `Spring Boot` con las siguientes dependencias:

> `/pom.xml`

~~~xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
</dependencies>
~~~

Adicionalmente debemos cargar la configuración de la base de datos y *Hibernate* a `spring.properties`

> `/src/main/resources/aplication.properties`

~~~sh
# Confuguración del datasource JDBC
spring.datasource.url = jdbc:mysql://localhost:3306/DB_NAME
spring.datasource.username = USERNAME
spring.datasource.password = PASSWORD

# Mantiene la conexión abierta siempre (producción)
spring.datasource.testWhileIdle = true
# Lanza un query para probar la conexión
spring.datasource.validationQuery = SELECT 1
# Muestra los comando SQL que va ejecutando
spring.jpa.show-sql = true
# Lanzar los comandos update inmediatamente
spring.jpa.hibernate.ddl-auto = update
# Naming Strategy (convenciones de nomenglatura)
spring.jpa.hibernate.naming-strategy = org.hibernate.cfg.ImprovedNamingStrategy
# Hibernate + MySQL
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
~~~

## 2. Capas del Negocio

Vamos a separar el negocio en `4` capas lógicas para tener una mejor organización y claridad de nuestro proyecto:

* `Capa de DATOS`: Se refiere a la capa que establece el modelo de los datos que serán almacenados en nuestro sistema, aquí colocaremos clases *POJO* (*Plain Old Java Object*) como los *DTO* (*Data Transactional Object*). En esta capa estableceremos las relaciones entre las entidades de nuestro sistema y sus atributos. Paquetes: `com.example.model`, `com.example.dto` o `com.example.data` (en `MVC` se suele llamar `com.example.model`).

* `Capa de ACCESO A DATOS`: Se refiere a la capa que interactua con la `Capa de DATOS` y con la *base de datos*. Está tiene el objetivo de recuperar y almacenar los datos para que persistan, dicha capa se suele llamar la capa de *Repositorio* o la capa *DAO* (*Data Access Object*). En está capa crearemos *interfaces* derivadas de la interfaz `CrudRepository<T, S>` donde `T` es la clase base del dato trasaccional (`DTO` o la entidad en cuestión) y `S` se refiere al tipo de dato del campo para la clave primaria (ej. `Long`). Paquetes: `com.example.dao` o `com.example.repository` (es más ferecuente este último).

* `Capa de SERVICIOS`: Se refiere a la capa primaria que realizará toda la lógica de nuestro sistema a través de métodos del servicio. Cada método representará una tarea específica que proveea nuestro sistema. Por ejemplo, buscar a alguna persona, realizar algunas operaciones con varias entidades, etc. La capa de servicios está fuertemente relacionada a la `Capa de ACCESO A DATOS` y brinda la seguridad para utilizar correctamente el repositorio, por ejemplo, validar que los datos sean correctos antes de guardarlos en el repositorio. Paquetes: `com.example.service`.

* `Capa del API`: Se refiere a la capa mediante la cual se van a exponer los servicios al usuario (programador) en forma de un `WEB API RESTFUL` (bajo el protocolo *HTTP* y los métodos *GET*, *POST*, *PUT*, *DELETE* principalmente). Está capa se encarga de exponer los *Paths* (las rutas *url*) y de recuperar los datos necesesarios de la *url* (los parámetros de la petición con `@RequestParam` y `@PathVariable`) para operar los servicios. Aquí no suele cargarse lógica compleja, sino más bien se limita a consumir la `Capa de SERVICIOS` y si acaso la `Capa de ACCESO A DATOS`. Paquetes: `com.example.api`, `com.example.restful`, `com.example.rest_controller` (la primera es la más utilizada).

* `Capa de VISTA-CONTROLADOR (Opcional)`: Cuando se está programado no sólo un `backend`, sino también un `frontend`, podemos proveer una capa extra para controlar nuestras vistas. En está capa establecemos algo similar a la `Capa del API`, con la diferencia que ahora nuestras rutas van a mapear *vistas* que provienen de archivos `html` principalmente.

### 2.1 Capa de Datos

Dentro de la capa de datos configuraremos los modelos (entidades) de la aplicación que queramos que persistan en base de datos o manipules frecuentemente. Esto lo haremos a través de clases planas y deberemos definir los campos de la entidad (*atributos*, *getters* y *setters*) y sobre ellos algunas anotaciones.

> Ejemplo: `/src/main/java/com/example/model/Cliente.java`

~~~java
package com.example.model;

// ... imports necesarios

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;

    String nombre;

    @NotNull
    String correo;

    @OneToOne
    Token token;

    // ... getters y setters

}
~~~

Algunas anotaciones importantes:

> Nivel Clase

* `@Table(name="clientes")`: Especifica la tabla específica a la que se quiere mapear, por defecto igual al nombre de la entidad en minúsculas (ej. `cliente`).

* `@NamedNativeQuery(name="Cliente.buscarPorCorreo", query="select * from cliente where correo like '%@:tipo.com'"), resultClass=Cliente.class)`: Especifíca un *query* nombrado que implementa la funcionalidad para el método `public List<Cliente> buscarPorCorreo(@Param("tipo") String tipo)` (vea `@Query` de la `Capa de ACCESO A DATOS`). Es utilizada cuando el *query* no puede ser mapeado de forma correcta en la `Capa de ACCESO A DATOS`.

> Nivel Propiedades

* `@OneToMany`: Establece una relación `uno a muchos`, lo cual significa que se espera dentro de la entidad una relación en la que a una entidad le correspondan varias entidades de otro tipo, pero las otras entidades sólo pueden tener una sóla entidad principal.

> Ejemplo: Supongamos que un cliente puede tener varios teléfonos, pero un teléfono sólo puede tener un sólo cliente.

~~~java
package com.example.model;

// ... imports

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Cliente {

    // ... otros campos

    @OneToMany(mappedBy="cliente")
    Set<Telefono> telefonos;

    // ... getters y setters

}
~~~

Observa que la anotación `@OneToMany` define `mappedBy="cliente"`, esto significa que la pertenencia principal de la relación pertenece al teléfono, es decir, que el cliente es creado independiente de sus teléfonos, pero cuando a un teléfono se le asigne un cliente, este será mapeado automáticamente al cliente.

Una observación más es la anotación `@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")`, la cual es necesaria para evitar que el `json` generado se vuelva recursivo en referencias bidireaccionales (úsalo siempre que la relación sea bidireccional). Por ejemplo, si el cliente necesita saber sus teléfonos, pero los teléfonos necesitan saber sus clientes se creará recursividad al generar el *json* debido a que el cliente mostrará sus teléfonos, pero cada teléfono mostrará su cliente y así sucesivamente, la solución más fácil es crear esa anotación a nivel clase.

* `@ManyToOne`: Establece una relación `muchos a uno`, lo cual significa que se espera dentro de la entidad una relación en la que muchas entidades puedan relacionarse con una entidad. Esto puede ser especificado para que la entidad contraria sepa quién es el actor principal y tener un bidireccionalidad de referencias.

> Ejemplo: Supongamos que un cliente puede tener varios télefonos, pero un teléfono sólo puede tener un sólo cliente y además queremos saber dentro del teléfono cuál es el cliente que le fue asignado.

~~~java
public class Telefono {

    // ... otros campos

    // el cliente tiene varios teléfonos, pero queremos
    // recuperar cuál es el cliente que le fue asignado a este en particular
    @ManyToOne
    Cliente cliente;

    // ... getters y setters

}
~~~

**Nota**: si no hay bidireccionalidad no hay necesidad de marcar en la anotación `@OneToMany` el `mappedBy`.

* `@ManyToMany`: Esta relación establece que varias entidades principales pueden tener varias entidades secundarias y viceversa, por ejemplo, una `Orden` podría tener varios productos asociados, pero un `Producto` podría tener también varias ordenes asignadas. El caso más complejo es el bidireccional donde necesitemos de la `Orden` saber todos sus productos, pero del `Producto` también queramos saber todas sus ordenes.

> Ejemplo: Supongamos que una orden tiene varios productos y un producto tiene varias ordenes.

#### NO BIDIRECCIONAL

~~~java
public class Orden {

    // ... otros campos

    @ManyToMany
    List<Producto> producto;

    // ... getters y setters

}
~~~

#### BIDIRECCIONAL

~~~java
public class Orden {

    // ... otros campos

    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.Merge})
    List<Producto> producto;

    // ... getters y setters

}
~~~

~~~java
public class Producto {

    // ... otros campos

    @ManyToMany(mappedBy="productos")
    List<Orden> ordenes;

    // ... getters y setters

}
~~~

### 2.2 Capa de Acceso a Datos

Dentro de la capa de acceso a datos configuraremos las interfaces que nos permitan realizar las operaciones *CRUD* y algunas otras búsquedas personalizadas.

> Ejemplo: `/src/main/java/com/example/repository/ClienteRepository.java`

~~~java
package com.example.repository;

// ... imports necesarios

@Transactional
public interface ClienteRespository extends CrudRepository<Cliente, Long> {

    @Query(value="select * from cliente where correo=:correo and clave=:clave limit 1", nativeQuery=true)
    public Optional<Cliente> buscarPorCorreoClave(@Param("correo") String correo, @Param("clave") String clave);

}
~~~

Observa que las interfaces son marcadas con la anotación `@Transactional`. También estamos creando una interfaz sin implementación, esto supone que vamos a dejar que *Hibernate* implemente la funcionalidad adecuada para que nuestro repositorio funcione.

Cuando queremos especificar una búsqueda detallada podemos especificar la anotación `@Query` la cual define el *query* específico que queremos ejecutar, cuando ese *query* no puede mapear correctamente la clase, por ejemplo, que el *query* tenga un `INNER JOIN` o interactue con otras entidades, entonces deberemos especificar el *query* mediante la anotación `@NameNativeQuery` dentro de la entidad como se mostró en la capa de datos. Esto es porque generalmente se regresa un `Object[]` como resultado del *query* y a veces no se puede mapear correctamente.

Observa detalladamente la anotación `@Param` que permite mapear nuestros parámetros dentro del *query* a través de `:campo`. Si queremos evitar esto, podríamos utilizar la posición del parámetro, por ejemplo:

~~~java
@Query(value="select * from cliente where correo=?1 and clave=?2 limit 1", nativeQuery=true)
public Optional<Cliente> buscarPorCorreoClave(String correo, String clave);
~~~

Lo cual no es muy recomendado porque aparecen número mágicos. Otra cosa a tomar en cuenta es marcar la anotación `@Modifying` si el *quey* no es de tipo `select`.

El regresar un `Optional<T>` donde `T` es la clase del dato transaccional, indica que no cause error en caso de que la búsqueda no pueda completarse y en dicho caso devolverá un `Optional` del que podemos ejecutar el método `isPresent()` para saber si devolvió algo y el método `get()` para recuperar el objeto de la clase `T` en caso de que si este presente.

Para consumir el repositorio simplemente llamaremos a sus métodos, por defecto, `CrudRepository` ya tiene definidos algunos métodos como `findById(id)` el cual busca una entidad por `id` y `save(entity)` que almacena una entidad, si es nueva la inserta y si ya existe la actualiza.

### 2.3 Capa de Servicios

En la capa de servicios vamos a definir las clases y sus métodos para operar correctamente los repositorios, deberiamos evitar que capas superiores accedan directamente al repositorio, al menos que se limiten a usar los métodos correspondientes a *queries* de búsquedas, pero si hacemos inserciones, actualizaciones o eliminaciones deberíamos proveer validaciones y solicitar tokens y toda esta lógica debería proveerla el servicio.

Generalmente el servicio debería recibir los datos transacionales ya estructurados y solo operarlos, para no estar recibiendo parámetros de tipos de datos que no pertenezcan a entidades, por ejemplo, para crear un nuevo cliente, lo correcto es recibir el cliente que se va a almacenar a tráves del repositorio y no los parámetros del cliente y tener que construirlo, por si después se modifica el dato transaccional no tener que afectar el servicio. También debemos ser específicos en los métodos, sobre todo cuando un mismo servicios opera diferentes entidades, así en lugar de proponer un método `crear(·)` deberíamos definir el método `crearCliente(·)` para ser más específicos, aunque existe la sobrecarga, está debemos limitarla a sobrecarga entre mismas entidades y no *cross* entidades.

> Ejemplo: `/src/main/java/com/example/repository/ClienteService.java`

~~~java
package com.example.service;

// ... imports necesarios

@Service
public interface ClienteService {

    @Autowired
    UtilService utilService;

    @Autowired
    TokenService tokenService;

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente crearCliente(Cliente cliente) {
        // Verifica que el correo del cliente este bien estructurdo
        if (!UtilService.validadCorreo(cliente)) {
            // El correo no está bien estructurado, deberíamos generar algún error o regresar nulo
            return null;
        }

        clienteRepository.save(cliente);

        return null;
    }

    public Token inciarSesión(Cliente cliente, ClienteCredenciales credenciales) {
        if (!SeguridadService.compararCredeciales(cliente.getCredenciales, credenciales)) {
            return null;
        }

        return TokenService.actualizarToken(cliente.getToken());
    }

}
~~~

Observa que el la clase tiene una anotación `@Service` y contiene referencias `@Autowired`, si hay dependencia cíclica recuerda poner la anotación adicional `@Lazy` en `@Autowired` para que pueda resolver la inyección de dependencias.

### 2.4 Capa del Api Web Restful

En la capa del Api Web Restful vamos a definir los controladores para las rutas del *backend* las cuales consumirán los servicios y opcionalmente los repositorios de nuestra aplicación. Lo más conveniente es definir la ruta base de todas las subrutas y en caso de trabajar la parte *frontend* se recomienda anteponer `/api/` para no limitar las rutas de las vistas.

Lo primero es crear una clase marcada con las anotaciones `@RestController` para proveer el control de rutas y `@RequestMapping` para especificar la ruta base del *api* a exponer, estas a nivel clase.

Es necesario seguir la filosofía de construcción de las rutas para que sea un servicio Restful, las reglas básicas sugieren utilizar los métodos `GET`, `POST`, `PUT` y `DELETE` (principalmente) correctamente. Piensa que `GET` son las rutas públicas sin información sensible, `POST` son las rutas ocultas que envían información sensible, las rutas `PUT` se limitan a crear entidades específicas y `DELETE` a eliminarlas. La nomenglatura básica de construcción es definir los verbos necesarios para construir las rutas y ensamblar los datos necesarios dentro de una ruta variable, por ejemplo, si queremos crear un cliente, su ruta sería `PUT /api/cliente :nombre=... :correo=...`, no `GET /api/cliente/nuevo?nombre=...&correo=...`, para visualizar al cliente con nombre de usuario `paco21` tendríamos `GET /api/cliente/paco21` y no `GET /api/cliente?nombreUsuario=paco21`, para eliminar al usuario `pepe123` tendríamos `DELETE /api/cliente/pepe123` y no `POST /api/cliente/eliminar :nombreUsuario=pepe123`.

Podemos utilizar `@RequestMapping(value="/{id}", method=RequestMethod.POST, produces="application/json")` o simplicarlo por `@PostMapping("/{id}") + @ResponseBody`.

Algo muy importante es inyectar dentro del método `HttpServletResponse response` para poder enviar diferentes estatus en la petición, sin comprometer el flujo normal, con la capacidad adicional de poder enviar un mensaje específico del por qué se produjo el error.

Cuando queremos recuperar una variable de la ruta utilizamos la anotación `@PathVariable("nombre")` a nivel parámetro de método y `@RequestParam("nombre")` nos permitirá recuperar un parámetro de la petición (indistinto si está en la url como *query* o dentro del cuerpo del formulario). Adicionalmente `@RequestParam` detecta el nombre del parámetro como nombre de la variable (ej. `@RequestParam String nombre` supone que el parámetro es nombre). Además `@RequestParam(defaultValue="123")` define el valor por defecto que tomará el parámtro.

> Ejemplo: `/src/main/java/com/example/repository/ClienteService.java`

~~~java
package com.example.api;

// ... imports necesarios

@RestController
@RequestMapping("/api/cliente")
public interface ClienteRestController {

    // ... autowireds

    @PutMapping("/")
    @ResponseBody
    public Cliente crearCliente(@RequestParam String nombre, @RequestParam String correo) {
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setCorreo(correo);

        return clienteService.crearCliente(cliente);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Cliente obtenerCliente(@PathVariable Long id, HttpServletResponse response) {
        Cliente cliente = clienteService.obtenerCliente(id);

        if (cliente == null) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "El cliente no existe :(");
            return null;
        }

        return cliente;
    }

}
~~~

Observa que en `crearCliente` inyectamos `HttpServletResponse response` el cual nos permitirá enviar un `bad request (status 400)` en caso de que el cliente no este presente en el repositorio. No olvides enviar `null` y con ello finalizar el método para que no continue.

### 2.5 Capa de Vista-Controlador (Opcional)

Hasta aquí ya tenemos la funcionalidad completa para programar nuestro *backend*, sin embargo podemos crear el `MVC` completo para brindar la `Capa de Vista-Controlador`.

La capa de Vista-Controlador se basa en brindar las interfaces web para consumir nuestros servicios a tráves de archivos html y un motor de plantillas. El motor utilizado en estas notas es `Thymeleaf` y puedes encontrar la documentación en https://www.thymeleaf.org

Lo primero es ajustar la dependencia en el `pom.xml`

~~~xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
~~~

Después deberemos especificar nuestras vistas en `src/main/resources/templates` (podemos agregar subcarpetas para mejor organización).

> Ejemplo: `src/main/resources/templates/cliente.html`

~~~html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
    <head>
    <meta charset="UTF-8">
    <title>Datos del Cliente</title>
    </head>
<body>
    <p th:text="${nombre}"></p>
    <p th:text="${correo}"></p>
</body>
</html>
~~~

Una vez definida la plantilla podremos consumirla definiendo la capa `vista-controlador` que consiste una clase con la anotación `@Controler` similar a `@RestController` con la diferencia que nuestros métodos regresaran un `String` con el nombre de la plantilla, ajustando los parámetros de la plantilla con la inyección de `Model model` en el método.

> Ejemplo: `src/main/java/com/example/web/ClienteController.java`

~~~java
package com.example.web;

// ... imports necesarios

@Controller
@RequestMapping("/cliente")
public interface ClienteController {

    // ... autowireds

    @GetMapping("/{id}")
    @ResponseBody
    public String verCliente(@PathVariable Long id, Model model, HttpServletResponse response) {
        Cliente cliente = clienteService.obtenerCliente(id);

        if (cliente == null) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "El cliente no existe :(");
            return null;
        }

        model.addAttribute("nombre", cliente.getCorreo());
        model.addAttribute("correo", cliente.getCorreo());

        return "cliente"; // se refiere a templates/cliente.html
    }

}
~~~

## 3. Seguridad

Para proveer una capa de seguridad, sería conveniente estructurar una entidad independiente a los usuarios donde se especifiquen las credenciales del usuario, por ejemplo, el *email*, el *password*, el *rol*, el *token*, la fecha de expiración del *token*, algunos servicios para determinar si el *token* es válido, validar que el token entre dentro de cierta operación, etc.

Lo primero será establecer la entidad *Credential* que representa la credencial con la que el usuario accede al sistema, esto independiente si el usuario ya definió campos para su correo u otros datos. La entidad credencial debería verse como la siguiente:

~~~java
package com.example.security;

// ... imports

@Entity
public class Credential {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;

    @NotNull
    String email;

    @NotNull
    @JsonIgnore
    String password;

    @NotNull
    String role;

    @NotNull
    String token;

    @NotNull
    Timestamp lastLogin;

    @NotNull
    Timestamp lastUpdate;

    @NotNull
    Boolean login;

	@NotNull
    String device;

    // ... getters y setters

}
~~~

Observa que todo lo referente a seguridad será separado en un paquete independiente mezclando, entidades, repositorios y servicios. También analiza el uso de esta entidad, la cuál servirá para autenticar al usuario, usaremos el campo `lastLogin` para saber cuando fue la última vez que ingreso sus credenciales y `lastUpdate` nos dirá cuando fue la ultima vez que actualizó sus credenciales (sólo el token). Si necesitaramos que los token expiren, entonces deberemos adicionar un campo `Timestamp expire`. El campo `login` nos dirá si el usuario tiene una sesión activa y el campo `device` podrá almacenar una cadena con el identificador de la máquina, por ejemplo, para advertirle al usuario que ha intentado inicar sesión desde otro dispositivo, en un sistema más avanzado podríamos hacer una relación `@OneToMany` sobre este campo, ya que el usuario podría iniciar sesión sus dispositivos de confianza. En esta metodología no se implementa el inicio de sesión en dos pasos.

Los siguiente es exponer un repositorio con la funcionalidad para saber si el usuario tiene una sesión activa, si el usuario inicia sesión después de cierto tiempo inactivo.

~~~java
package com.example.security;

// ... imports

@Transactional
public interface CredentialRepository extends CrudRepository<Credential, Long> {

    @Query(value="select replace(uuid(),'-','')", nativeQuery=true)
    public String randomToken();

    @Query(value="select * from credential where email=:email and password=:password limit 1", nativeQuery=true)
    public Optional<Credential> checkCredential(@Param("email") String email, @Param("password") String password);

    @Query(value="select * from credential where email=:email and password=:password and role=:role limit 1", nativeQuery=true)
    public Optional<Credential> checkCredentialAndRole(@Param("email") String email, @Param("password") String password, @Param("role") String role);

    @Modifying
    @Query(value="update credential set last_login=now(), last_update=now(), login=1, token=replace(uuid(),'-','') where email=:email", nativeQuery=true)
    public void doLogin(@Param("email") String email);

    @Modifying
    @Query(value="update credential set login=0, token=replace(uuid(),'-','') where email=:email", nativeQuery=true)
    public void doLogout(@Param("email") String email);

    @Query(value="select * from credential where email=:email and token=:token limit 1", nativeQuery=true)
    public Optional<Credential> checkToken(@Param("email") String email, @Param("token") String token);

    @Query(value="select * from credential where email=:email and token=:token and time_to_sec(timediff(now(), last_update))<=:seconds limit 1", nativeQuery=true)
    public Optional<Credential> checkTokenWithExpiration(@Param("email") String email, @Param("token") String token, @Param("seconds") Long seconds);

    @Query(value="select * from credential where email=:email and token=:token and device=:device limit 1", nativeQuery=true)
    public Optional<Credential> checkTokenWithDevice(@Param("email") String email, @Param("token") String token, @Param("device") String device);

    @Query(value="select * from credential where email=:email and token=:token and device=:device and time_to_sec(timediff(now(), last_update))<=:seconds limit 1", nativeQuery=true)
    public Optional<Credential> checkTokenWithDeviceAndExpiration(@Param("email") String email, @Param("token") String token, @Param("device") String device, @Param("seconds") Long seconds);

}
~~~

Luego preoveeremos los servicios de seguridad para ingresar al sistema, salir del sistema o validar un usuario y rol.

~~~java
package com.example.security;

// ... imports

@Service
public class CredentialService {

    // ... autowireds

    final Long EXPIRATION_TIME = 24l * 60l * 60l;

    public Credential createCredential(Credential credential) {
    	Timestamp date = Timestamp.valueOf(LocalDateTime.now());
    	
    	credential.setLastLogin(date);
    	credential.setLastUpdate(date);
    	credential.setToken(credentialRepository.randomToken());
    	credential.setLogin(true);
    	
        credentialRepository.save(credential);

        return credential;
    }

    public Credential doLogin(Credential credential) {
        Optional<Credential> authenticOptional = credentialRepository.checkCredentialAndRole(credential.getEmail(), credential.getPassword(), credential.getRole());

        if (!authenticOptional.isPresent()) {
            return null;
        }

        Credential authentic = authenticOptional.get();

        Timestamp date = Timestamp.valueOf(LocalDateTime.now());
        
        authentic.setLastLogin(date);
        authentic.setLastUpdate(date);
    	authentic.setToken(credentialRepository.randomToken());
    	authentic.setLogin(true);
    	authentic.setDevice(credential.getDevice());

        credentialRepository.save(authentic);

        return authentic;
    }

    public Credential refreshToken(Credential credential) {
        Optional<Credential> authenticOptional = credentialRepository.checkTokenWithDeviceAndExpiration(credential.getEmail(), credential.getToken(), credential.getDevice(), EXPIRATION_TIME);

        if (!authenticOptional.isPresent()) {
            return null;
        }

        Credential authentic = authenticOptional.get();

        Timestamp date = Timestamp.valueOf(LocalDateTime.now());
        
        authentic.setLastUpdate(date);
    	authentic.setToken(credentialRepository.randomToken());
    	authentic.setLogin(true);

        credentialRepository.save(authentic);

        return authentic;
    }

    public Credential getCredential(Credential credential) {
        Optional<Credential> authenticOptional = credentialRepository.checkTokenWithDeviceAndExpiration(credential.getEmail(), credential.getToken(), credential.getDevice(), EXPIRATION_TIME);

        if (!authenticOptional.isPresent()) {
            return null;
        }

        Credential authentic = authenticOptional.get();

        return authentic;
    }

    public Credential doLogout(Credential credential) {
        Optional<Credential> authenticOptional = credentialRepository.checkTokenWithDeviceAndExpiration(credential.getEmail(), credential.getToken(), credential.getDevice(), EXPIRATION_TIME);

        if (!authenticOptional.isPresent()) {
            return null;
        }

        Credential authentic = authenticOptional.get();
        
    	authentic.setToken(credentialRepository.randomToken());
    	authentic.setLogin(false);

        credentialRepository.save(authentic);

        return authentic;
    }

}
~~~

Ahora vamos a proveer el `api` de seguridad que nos permitirá realizar autenticaciones.

~~~java
package com.example.security;

// ... imports

@RestController
@RequestMapping("/api/auth")
public class CredentialApi {

    // ... autowireds

    @PostMapping("/login")
    @ResponseBody
    public Credential doLogin(@RequestParam String email, @RequestParam String password, @RequestParam(defaultValue="EMPTY-DEVICE") String device, @RequestParam(defaultValue="EMPTY-ROLE") String role, HttpServletResponse response) throws IOException {
        Credential credential = new Credential();
        credential.setEmail(email);
        credential.setPassword(password);
        credential.setDevice(device);
        credential.setRole(role);

        Credential authentic = credentialService.doLogin(credential);

        if (authentic == null) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Unathorized");
            return null;
        }

        return authentic;
    }

    @GetMapping("/logout")
    @ResponseBody
    public Credential doLogout(@RequestParam String email, @RequestParam String token, @RequestParam(defaultValue="EMPTY-DEVICE") String device, HttpServletResponse response) throws IOException {
        Credential credential = new Credential();
        credential.setEmail(email);
        credential.setToken(token);
        credential.setDevice(device);

        Credential authentic = credentialService.doLogout(credential);

        if (authentic == null) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Unathorized");
            return null;
        }

        return authentic;
    }

    @GetMapping("/token")
    @ResponseBody
    public Credential refreshToken(@RequestParam String email, @RequestParam String token, @RequestParam(defaultValue="EMPTY-DEVICE") String device, HttpServletResponse response) throws IOException {
        Credential credential = new Credential();
        credential.setEmail(email);
        credential.setToken(token);
        credential.setDevice(device);

        Credential authentic = credentialService.refreshToken(credential);

        if (authentic == null) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Token has been expired or revoked");
            return null;
        }

        return authentic;
    }

    @PutMapping("")
    @ResponseBody
    public Credential createCredential(@RequestParam String email, @RequestParam String password, @RequestParam(defaultValue="EMPTY-DEVICE") String device, @RequestParam(defaultValue="EMPTY-ROLE") String role, HttpServletResponse response) throws IOException {
        Credential credential = new Credential();
        credential.setEmail(email);
        credential.setPassword(password);
        credential.setDevice(device);
        credential.setRole(role);

        Credential authentic = credentialService.createCredential(credential);

        if (authentic == null) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error creating credential");
            return null;
        }

        return authentic;
    }

    @GetMapping("")
    @ResponseBody
    public Credential getCredential(@RequestParam String email, @RequestParam String token, @RequestParam(defaultValue="EMPTY-DEVICE") String device, HttpServletResponse response) throws IOException {
        Credential credential = new Credential();
        credential.setEmail(email);
        credential.setToken(token);
        credential.setDevice(device);

        Credential authentic = credentialService.getCredential(credential);

        if (authentic == null) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Token has been expired or revoked");
            return null;
        }

        return authentic;
    }

}
~~~

## 4. Despliegue en Heroku

En construcción...

## 5. Pruebas unitarias

En construcción...

## Ejercicios

* Modela un negocio, por ejemplo, para administrar una tienda, una plataforma como *Uber*, entre otros. Establece correctamente cuales serán las entidades que se manejarán, por ejemplo, Cliente, Producto, Orden, etc.

* Crea la capa de datos especificando correctamente las relaciones entre tus entidades.

* Crea la capa de acceso a datos, pensando todos los *queries* y métodos de búsqueda que necesitarás.

* Crea la capa de servicios, pensando en las tareas que tendrá que resolver tu negocio, como crear una orden, asignar el cliente, asignar el vendedor, agregar un producto a la orden, calcular el total de la orden, realizar el cobro al cliente, realizar el pago al vendedor, etc.

* Crea la capa del api, pensando en como exponer correctamente tus servicios, por ejemplo, PUT /api/orden necesita recibir quizás el `idCliente`, `idVendedor`, etc.

* Crea la capa de vista-controlador para consumir tus servicios, por ejemplo, un formulario que muestre un botón para crear una orden nueva, una página para buscar usuarios por nombre, etc.

* Agrega seguridad a tu sistema para que las operaciones delicadas sólo puedan ser efectuadas con un *token*

* Despliega tu servidor en `Heroku` con una base de datos `ClearDB MySql`.

* Realiza diversas pruebas unitarias para verificar que tu sistema funcione correctamente.