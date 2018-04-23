# Examen de Evaluación del Curso

Por Alan Badillo Salas (badillo.soft@hotmail.com)

## Introuducción

El examen consiste en `20` preguntas abiertas divididas en 5 preguntas por módulo. Resuelva las preguntas lo más breve posible.

## Módulo I. Conocimientos de Java

1. Defina la clase `Empleado` con los atributos `Long id`, `String nombre`, `String Correo`, `Departamento departamento`,  `Puesto puesto`. No olvide colocar los métodos `Getter` y `Setter`.

2. A la clase `Empleado` sobrecargue el método `String toString()` para que devuelva la concatenación de la propiedad `id`, `nombre` y `correo` con el formato: `[ID] NOMBRE (CORREO)`.

3. Cree un método constructor para la clase `Empleado` que reciba `(Long id, String nombre, String correo)`.

4. Cree la clase `EmpleadoNAAt` derivada de la clase `Empleado` agregando el atributo `Double salario`.

5. Modifique el método sobrecargado `String toString()` en la clase `EmpleadoNAAT` para que devuelva la concatenación de `id`, `nombre`, `correo` y `salario` con el formato: `[ID] NOMBRE (CORREO) $SALARIO`.

## Módulo II. Conocimientos de SQL

1. Describa el `query` para la que la clase `EmpleadoNAAT` persista en la base de datos en la tabla `empleados`. Hint: Utiliza `CREATE TABLE`.

2. Describe el `query` para insertar un nuevo registro en la tabla `empleados` creada en el punto anterior.

3. Describe el `query` para actualizar un registro de la tabla `empleados` ajustando `salario=100000.5` cuyo correo sea cómo `@na-at.com`.

4. Describe el `query` para eliminiar un registro de la tabla `empleados` dónde `nombre='Pepe Pecas'`.

5. Describe el `query` para buscar a todos los empleados de la tabla `empleados` cuyo correo es cómo `@na-at.com` y su salario es menor o igual a `5000`.

## Módulo III. Conocimientos de Spring

1. Describe las `4` capas para organizar correctamente el código de un negocio/plataforma.

2. ¿Cuál es la anotación utilizada para marcar una clase como un servicio de `Spring`?

3. ¿Cómo se define un repositorio en `Spring`? Indica la anotación a nivel clase/interfaz y la herencia relacionada especificando en para qué es utilizada dicha herencia y sus posibilidades.

4. ¿Cuál es la anotación a nivel clase para crear un `API RESTFUL` en `Spring`?

5. Describe el código necesario para generar una ruta con método `PUT` sobre el *PATH* `/empleado` que reciba `String nombre`, `String correo` y `Double salario` de los parámetros de la petición e inyecte el objecto `HttpServletResponse response` (puede omitir el contenido del método, sólo defina las anotaciones y la estructura del método).

## Módulo IV. Conocimientos de Hibernate y Seguridad

1. ¿Para qué sirve la anotación `@Entity` y dónde es colocada?

2. ¿Para qué se utiliza la anotación `@ManyToMany` y dónde se coloca? Especifica también un ejemplo.

3. Recrea un ejemplo del `query` nativo: `SELECT E FROM empleado E WHERE correo=:correo AND salario>=20000` en el `EmpleadoRepository`. Coloca todo el código del repositorio y cómo debería utilizarse ese `query`.

4. Supongamos que tenemos un sistema basado en `tokens` que al iniciar sesión con las credenciales de un usuario le devuelve al usuario un `token` que es válido por sus credenciales originales. Describe un ejemplo de la ruta `PUT /empleado` que reciba el `String nombre`, `String correo` y `Double salario` de `@RequestParam` y modificalo para recibir además el parámetro `String token` para utilizar un servicio ficticio llamado `credentialService` en el método `isValidToken(String correo, String token)` que devuelve un `true` o `false` dependiendo si el `token` para ese correo es válido y devuelve un `null` en caso de que el `token` no sea válido, en otro caso coloca `// ... el token si es válido`.

5. Modifica el método anterior para devovler un `BAD REQUEST` con el mensaje `NO AUTORIZADO`.