package com.loja_virtual_sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.loja_virtual_sb.model")
public class LojaVirtualSbApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaVirtualSbApplication.class, args);
	}

}
