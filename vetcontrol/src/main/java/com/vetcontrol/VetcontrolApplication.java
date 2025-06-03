package com.vetcontrol;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VetcontrolApplication implements CommandLineRunner {

	// Inyecta el perfil activo
	@Value("${spring.profiles.active:default}")
	private String perfilActivo;

	public static void main(String[] args) {
		SpringApplication.run(VetcontrolApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("✅ VetControl iniciado con el perfil: " + perfilActivo.toUpperCase());
	}
}
