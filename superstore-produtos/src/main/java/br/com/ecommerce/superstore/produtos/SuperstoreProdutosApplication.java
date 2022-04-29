package br.com.ecommerce.superstore.produtos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SuperstoreProdutosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperstoreProdutosApplication.class, args);
    }

}
// nao posso exibir produtos sem categorias
// nao posso exibir produtos que nao tem em estoque
// nao posso exibir produtos que tem algum campo nullo
