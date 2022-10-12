package tup.simple;

/*
Esta es la clase principal de la aplicación.
Vemos que solo tiene las anotaciones de SpringBoot.
*/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Esta anotación es muy importante. Hace automáticamente
 * una serie de inicializaciones, lo cual es cómodo para nosotros.
 * Pero además habilita el classpath scanning. Esto quiere decir
 * que el sistema va a buscar automáticamente entre todos los
 * paquetes que componen el sistema, todas las clases que estén
 * marcadas como candidatas a inyección de dependencia. Entonces,
 * cuando las necesite sabrá dónde enconntrarlas.
 */
@SpringBootApplication
public class SimpleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleApplication.class, args);
	}

}
