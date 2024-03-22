package com.dh.ClinicMVC;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ClinicMvcApplication {
	// manejar el error 500 por el tipo de columna unique
	// turnos, como asociar un odontologo y un paciente desde el front,
	// donde el odontologo y el paciente ya estan creados
	public static void main(String[] args) {
		SpringApplication.run(ClinicMvcApplication.class, args);
	}
	// turno{ paciente: null
	// odontologo: null }
	//paciente{}
	//odontologo{}

}