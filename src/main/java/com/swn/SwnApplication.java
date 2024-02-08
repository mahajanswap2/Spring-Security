package com.swn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class SwnApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwnApplication.class, args);
	}
	
	@RequestMapping(value="/test")
	   public String index() {
		return"<h1> Welcome to Software Workshop <h1>";
		
		
		}
	

}
