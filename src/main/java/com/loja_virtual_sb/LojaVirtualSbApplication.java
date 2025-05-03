package com.loja_virtual_sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan("com.loja_virtual_sb.model")
@ComponentScan("com.*")
@EnableJpaRepositories(basePackages = "com.loja_virtual_sb.repository")
@EnableTransactionManagement
public class LojaVirtualSbApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaVirtualSbApplication.class, args);
	}

}
