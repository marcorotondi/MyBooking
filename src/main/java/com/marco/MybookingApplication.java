package com.marco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.marco.configuration.WebSecurityConfig;

@SpringBootApplication
@Import({WebSecurityConfig.class})
public class MybookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybookingApplication.class, args);
	}
}
