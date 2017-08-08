package br.com.personal.money;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.com.personal.money.config.PersonalMoneyProperty;

@SpringBootApplication
@EnableConfigurationProperties(PersonalMoneyProperty.class)
public class PersonalMoneyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalMoneyApiApplication.class, args);
	}
}
