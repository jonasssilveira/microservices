package br.com.ecommerce.superstore.usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient

public class MSUsuario {

    public static void main(String[] args) {
        SpringApplication.run(MSUsuario.class, args);
    }

}
