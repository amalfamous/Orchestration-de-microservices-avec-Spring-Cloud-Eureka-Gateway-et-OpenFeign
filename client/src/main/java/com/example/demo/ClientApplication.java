package com.example.demo;

import com.example.demo.entities.Client;
import com.example.demo.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient // Active l'enregistrement auprès d'Eureka
@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}
	@Bean
	CommandLineRunner initDatabase(ClientRepository clientRepository) {
		return args -> {
			Client client1 = new Client();
			client1.setNom("Amal Famous");
			client1.setAge(22.0f);
			clientRepository.save(client1);

			Client client2 = new Client();
			client2.setNom("Fatima Zahra");
			client2.setAge(30.0f);
			clientRepository.save(client2);
		};
	}
}
