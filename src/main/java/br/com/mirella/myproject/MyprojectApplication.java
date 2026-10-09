package br.com.mirella.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Junta tres funções:
// @SpringBootConfiguration → indica a configuração principal da aplicação.
// @EnableAutoConfiguration → configura automaticamente o que a aplicação precisa.
// @ComponentScan → indica ao Spring para procurar outros componentes, configurações e serviços no mesmo pacote
public class MyprojectApplication {

	public static void main(String[] args) {

		// Inicia a aplicação Spring Boot.
        // A classe MyprojectApplication é usada como configuração inicial.
		SpringApplication.run(MyprojectApplication.class, args);
	}

}  
