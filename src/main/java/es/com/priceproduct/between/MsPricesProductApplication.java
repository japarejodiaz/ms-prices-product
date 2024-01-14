package es.com.priceproduct.between;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"es.com.priceproduct.between.repository"})
public class MsPricesProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPricesProductApplication.class, args);
	}

}
