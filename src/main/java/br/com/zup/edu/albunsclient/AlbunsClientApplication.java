package br.com.zup.edu.albunsclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AlbunsClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlbunsClientApplication.class, args);
	}

}
