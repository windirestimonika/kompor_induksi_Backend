package com.iconpln.kompor_induksi_Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.iconpln.kompor_induksi_Backend.assembler"})
@SpringBootApplication
public class KomporInduksiBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(KomporInduksiBackendApplication.class, args);
	}

}
