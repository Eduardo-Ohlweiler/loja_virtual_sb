package com.loja_virtual_sb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.loja_virtual_sb.controller.AcessoController;
import com.loja_virtual_sb.model.Acesso;
//import com.loja_virtual_sb.service.AcessoService;

@SpringBootTest(classes = LojaVirtualSbApplication.class)
class LojaVirtualSbApplicationTests {

	//@Autowired
	//private AcessoService acessoService;

	@Autowired
	private AcessoController acessoController;

	@Test
	void testCadastraAcesso() {

		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_ADMIN");
		acessoController.salvarAcesso(acesso);
	}

}
