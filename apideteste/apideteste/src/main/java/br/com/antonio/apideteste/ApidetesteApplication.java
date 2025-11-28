package br.com.antonio.apideteste;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class ApidetesteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApidetesteApplication.class, args);
	}

}
