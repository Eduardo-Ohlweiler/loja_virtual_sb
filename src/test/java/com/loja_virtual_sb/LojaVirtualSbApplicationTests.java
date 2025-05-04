package com.loja_virtual_sb;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.loja_virtual_sb.controller.AcessoController;
import com.loja_virtual_sb.model.Acesso;
//import com.loja_virtual_sb.service.AcessoService;
import com.loja_virtual_sb.repository.AcessoRepository;

@SpringBootTest(classes = LojaVirtualSbApplication.class)
class LojaVirtualSbApplicationTests {

	@Autowired
	private AcessoController acessoController;

	@Autowired
	private AcessoRepository acessoRepository;

	@Test
	void testCadastraAcesso() {

		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_ADMIN");
		acesso = acessoController.salvarAcesso(acesso).getBody();

		assertEquals(true, acesso.getId() > 0);
		assertEquals("ROLE_ADMIN", acesso.getDescricao());

		
		//Teste de carregamento
		Acesso acesso2 = acessoRepository.findById(acesso.getId()).get();

		assertEquals(acesso.getId(), acesso2.getId());


		//TESTE DE DELETE
		acessoRepository.deleteById(acesso2.getId());
		acessoRepository.flush();

		Acesso acesso3 = acessoRepository.findById(acesso2.getId()).orElse(null);
		assertEquals(true, acesso3 == null);


		//TESTE DE QUERY
		acesso = new Acesso();
		acesso.setDescricao("ROLE_ALUNO");
		acesso = acessoController.salvarAcesso(acesso).getBody();

		List<Acesso> acessos = acessoRepository.buscarAcessoDesc("ALUNO".trim());
		assertEquals(1, acessos.size());
		acessoRepository.deleteById(acesso.getId());
	}

	

}
