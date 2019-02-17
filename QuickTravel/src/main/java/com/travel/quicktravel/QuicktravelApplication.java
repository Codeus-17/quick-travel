package com.travel.quicktravel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class QuicktravelApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(QuicktravelApplication.class, args);
	}

        @Bean
        public BCryptPasswordEncoder encode()
        {
            return new BCryptPasswordEncoder();
        }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Password:: "+ new BCryptPasswordEncoder().encode("password"));
    }
        
        
}
