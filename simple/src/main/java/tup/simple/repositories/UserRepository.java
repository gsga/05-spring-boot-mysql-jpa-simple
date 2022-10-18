package tup.simple.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tup.simple.models.User;

/**
 * Esta anotación le indica a Spring Boot que las clases
 * que implementan esta interfaz UserRepository son candidatas a la inyección
 * de dependencias. Esto significa que, sin nosotros programar nada,
 * podemos tener todas las funcionalidades incorporadas a nuestro
 * programa. Lo único que tenemos que hacer es poner las anotaciones
 * necesarias en el lugar apropiado.
 * Esta es una de las anotaciones que necesitamos. En esta interfaz
 * UserRepository ya no necesitamos hacer nada más.
 * La clase que hace todo el trabajo en este ejemplo es
 * UserController. La clase UserController es la que va a recibir la inyección
 * de la dependencia. Esto significa que la clase UserController va a tener una
 * variable declarada del tipo UserRepository. Solo la va a declarar, no vamos
 * a programar nada, o casi nada.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
