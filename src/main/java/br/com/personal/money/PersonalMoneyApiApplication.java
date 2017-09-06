package br.com.personal.money;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.personal.money.config.PersonalMoneyProperty;
import br.com.personal.money.repository.impl.JpaSpecificationExecutorProjectionImpl;

@SpringBootApplication
@EnableConfigurationProperties(PersonalMoneyProperty.class)
@EnableJpaRepositories(repositoryBaseClass=JpaSpecificationExecutorProjectionImpl.class)
public class PersonalMoneyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalMoneyApiApplication.class, args);
	}
}
