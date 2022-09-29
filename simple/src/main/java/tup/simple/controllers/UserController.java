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
// La URL que vaya en la anotación habrá que agregarla detrás del puerto :8080
// en todas las llamadas a esta aplicación.
// Por ejemplo @RequestMapping("/user") resultaría en lo siguiente:
// localhost:8080/user.... y detrás de esto habría que agregar el
// resto de la URL.
// En este caso, no necesitamos nada, y queda simplemente localhost:8080
@RequestMapping("")
public class UserController {
  /**
   * Esto significa que Spring va a inyectar en esta clase un bean
   * llamado userRepository.
   * No hay en este proyecto una clase UserRepository. Solo hay una
   * interfaz UserRepository. Y esta interfaz lo único que hace es extender
   * CrudRepository. No declara ni campos ni métodos. Nosotros no hacemos nada,
   * todo lo hace Spring por nosotros.
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

  @GetMapping("/all")
  public String getAllUsers() {
    // This returns a JSON or XML with the users
    Iterable<User> iterable = userRepository.findAll();
    /**
     * No es buen estilo incluir cadenas largas en un archivo de código fuente.
     * Esto lo hago solo para no introducir una complicación que no agregaría
     * nada a los conceptos que estoy discutiendo ahora.
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
    // No puedo usar forEach() con una función lambda
    // porque el scope de las variables no lo permite.
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
