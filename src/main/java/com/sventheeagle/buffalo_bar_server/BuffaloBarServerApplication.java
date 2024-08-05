package com.sventheeagle.buffalo_bar_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BuffaloBarServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuffaloBarServerApplication.class, args);
	}

	@GetMapping("/")
	public String greet() {
		return "Hello";
	}
}
