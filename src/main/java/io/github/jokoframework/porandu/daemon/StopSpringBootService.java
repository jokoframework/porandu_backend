package io.github.jokoframework.porandu.daemon;

/**
 * Stop a Spring Boot application as a service.
 *
 * @author Stephane Nicoll
 */
public class StopSpringBootService {

	public static void main(String[] args) throws Exception {
		new SpringBootService().stop(args);
	}

}
