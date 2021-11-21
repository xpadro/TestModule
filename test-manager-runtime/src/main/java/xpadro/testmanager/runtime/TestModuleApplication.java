package xpadro.testmanager.runtime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestModuleApplication {

	// Running the application locally requires a MongoDB server (mongod) listening to port 27017
	public static void main(String[] args) {
		SpringApplication.run(TestModuleApplication.class, args);
	}

}
