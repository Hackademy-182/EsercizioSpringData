package it.aulab.esercizio_spring_data;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EsercizioSpringDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsercizioSpringDataApplication.class, args);
	}

	@Bean
	public ModelMapper instanceModelMapper(){
		ModelMapper mapper = new ModelMapper();
		return mapper;
	}

}
