package produtos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableEurekaClient
public class SuperstoreProdutoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperstoreProdutoApplication.class, args);
    }

}
// nao posso exibir produtos sem categorias
// nao posso exibir produtos que nao tem em estoque
// nao posso exibir produtos que tem algum campo nullo
