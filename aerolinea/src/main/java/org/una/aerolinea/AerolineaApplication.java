package org.una.aerolinea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2 
@SpringBootApplication
public class AerolineaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AerolineaApplication.class, args);
	}

}
