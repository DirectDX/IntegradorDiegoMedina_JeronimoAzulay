package com.dh.ClinicMVC;


import com.dh.ClinicMVC.dao.BD;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ClinicMvcApplication {

	public static void main(String[] args) {
		BD.crearTablas();
		SpringApplication.run(ClinicMvcApplication.class, args);
	}
}