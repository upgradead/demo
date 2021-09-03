package com.ivan.cayabyab.gofluent;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class GofluentApplication {



	public static void main(String[] args) {
		SpringApplication.run(GofluentApplication.class, args);
	}
}
