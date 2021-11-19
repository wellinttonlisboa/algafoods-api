package br.com.algaworks.algafoods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.algaworks.algafoods.repository.impl.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AlgafoodsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgafoodsApiApplication.class, args);
	}

}
