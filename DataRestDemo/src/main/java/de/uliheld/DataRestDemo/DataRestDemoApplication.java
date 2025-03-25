package de.uliheld.DataRestDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;

@SpringBootApplication
public class DataRestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataRestDemoApplication.class, args);
	}

}

interface StuffRepository extends CrudRepository<Stuff, Long> {
}
