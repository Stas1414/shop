package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(new BCryptPasswordEncoder().encode("1234"));//stas
		System.out.println(new BCryptPasswordEncoder().encode("wwwwww"));//zhenya
		System.out.println(new BCryptPasswordEncoder().encode("06102004"));//alekhnovich
		System.out.println(new BCryptPasswordEncoder().encode("5310954"));//dmitrievna
	}
}
