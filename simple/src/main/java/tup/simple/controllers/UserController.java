/**
 * Esta clase UserController pertenece al paquete controllers.
 * En principio, solo debería atender a los requests HTTP,
 * y según sea GET, POST, u otro método, analizar el contenido
 * del request, y decidir a qué método llamar. 
 * En principio, esta clase no debería hacer el trabajo. No.
 * Lo que debería hacer es llamar al método encargado de hacer
 * el trabajo y pasarle los parámetros necesarios. 
 * Ese método llamado debería pertenecer a una clase del paquete services. 
 * Pero nosotros no tenemos ese paquete, porque este es un ejemplo muy simple.
 * Veremos que esta clase hace el trabajo, lo que no debería ser así.
 * Entonces, recordar que estamos dejando de lado un principio
 * muy importante, para no complicar este ejemplo.
 */
package tup.simple.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tup.simple.models.User;
import tup.simple.repositories.UserRepository;

/**
 * La anotación @RestController es la combinación de @Controller
 * y @ResponseBody.
 * Está anotando la clase, o sea que todos los métodos la heredan y
 * no es necesario anotar cada uno de ellos. Todos tendrán la semántica
 * de @ResponseBody. Esto significa que la String retornada es la response, no
 * el nombre de una vista.
 */
@RestController
// La URL que vaya en la anotación habrá que agregarla detrás del puerto 8080
// en todas las llamadas a esta aplicación.
// Por ejemplo @RequestMapping("/user") resultaría en lo siguiente:
// localhost:8080/user.... y detrás de esto habría que agregar el
// resto de la URL.
// En este caso, no necesitamos nada, y queda simplemente localhost:8080
@RequestMapping("")
public class UserController {
  /**
   * Qué es un bean en Java
   * https://stackoverflow.com/a/3295517/2740402
   * Un JavaBean es solo un estándar. Es una clase regular de Java, excepto que
   * sigue ciertas convenciones:
   * - Todas las propiedades son privadas (usan getters y setters).
   * - Tiene un constructor público sin argumentos.
   * - Implementa la interfaz Serializable.
   * Eso es todo. Es solo una convención.
   * 
   * La anotación @Autowired significa que Spring va a inyectar en esta clase un
   * bean
   * llamado userRepository.
   * No hay en este proyecto una clase UserRepository. Solo hay una
   * interfaz UserRepository. Y esta interfaz lo único que hace es extender
   * CrudRepository. No declara ni campos ni métodos. Nosotros no hacemos nada,
   * todo lo hace Spring por nosotros.
   * Esta es la inyección de dependencia. Nosotros lo único que hacemos es
   * declarar la variable userRepository de tipo UserRepository, y ponerle
   * la anotación Autowired. Y listo. Ya tenemos en esta clase UserController
   * la variable userRepository correctamente configurada e inicializada, de
   * manera que la podemos usar sin más.
   * Notar que tampoco hemos programado los métodos que estamos llamando. Esos
   * métodos fueron generados automáticamente por la anotación Data de Lombok,
   * que pusimos en la clase User.
   */
  @Autowired
  private UserRepository userRepository;

  @PostMapping("/add") // Map ONLY POST Requests
  public String addNewUser(@RequestParam String name, @RequestParam String email) {
    // @RequestParam means it is a parameter from the GET or POST request

    User n = new User();
    n.setName(name);
    n.setEmail(email);
    userRepository.save(n);
    return "Saved";
  }

  @PostMapping("/delete/{id}") // Map ONLY POST Requests
  public String deleteUserById(@PathVariable Long id) {
    // @RequestParam means it is a parameter from the GET or POST request

    userRepository.deleteById(id);
    return "Deleted";
  }

  @GetMapping("/{id}")
  public String findUserById(@PathVariable Long id) {
    // @PathVariable indica que el parámetro id, de tipo Long, es una
    // variable que viene en la URI.
    /**
     * https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html#findById-ID-
     * Optional<T> findById(ID id)
     */
    return userRepository.findById(id).toString();
  }

  @GetMapping("/all")
  public String getAllUsers() {
    // This returns a JSON or XML with the users
    Iterable<User> iterable = userRepository.findAll();
    /**
     * Lo que viene a continuación se llama text block, 
     * y es tipo String. El Manual de Java los describe en 
     * la sección 3.10.6 Text Blocks.
     * 
     * La variable resp es de tipo String, y le vamos a asignar un bloque de texto.
     * Ese bloque de texto es todo que lo que está contenido entre los dos
     * delimitadores: el de apertura y el de cierre.
     * El delimitador de apertura es la triple comilla """ que está a la
     * derecha del igual.
     * El delimitador de cierre es la triple comilla """ que está al final.
     * Todo es seguido por el punto y coma, porque es el final de una sentencia.
     * 
     * No es buen estilo incluir cadenas largas en un archivo de código fuente.
     * Esto lo hago solo para no introducir una complicación que no agregaría
     * nada a los conceptos que estoy discutiendo ahora.
     * 
     * Comenzamos por poner unos estilos CSS, para que la tabla quede más linda.
     * 
     * Cuando terminamos con los estilos, arrancamos con el HTML de la
     * tabla misma. Lo primero que hacemos es generar una fila y en las
     * celdas de esa fila poner los encabezados, que son los nombres de
     * las columnas o campos de la tabla que está en la base de datos.
     */
    String resp = """
          <style>
            #users {"
              font-family: Arial, Helvetica, sans-serif;
              border-collapse: collapse;
              width: 100%;
            }
            #users td, #users th {
              border: 1px solid #ddd;
              padding: 8px;
            }
            #users tr:nth-child(even){background-color: #f2f2f2;}
            #users tr:hover {background-color: #ddd;}
            #users th {
              padding-top: 12px;
              padding-bottom: 12px;
              text-align: left;
              background-color: #04AA6D;
              color: white;
            }
          </style>
          <table id ='users'>
            <tr>
              <th>Id</th>
              <th>Name</th>
              <th>Email</th>
            </tr>
        """;
        /**
         * Ya terminé con la fila de los encabezados, y ahora tengo que
         * generar el cuerpo de la tabla, una fila por cada registro.
         * No puedo usar forEach() con una función lambda
         * porque el scope de las variables no lo permite.
         * Por eso uso el for mejorado, para recorrer el objeto iterable.
         */
    for (User user : iterable) {
      resp += "<tr>"
          + "<td>" + user.getId() + "</td>"
          + "<td>" + user.getName() + "</td>"
          + "<td>" + user.getEmail() + "</td>"
          + "</tr>";
    }
    return resp + "</table>";
  }

  @GetMapping("")
  public String hola() {
    return "Hola";
  }
}
