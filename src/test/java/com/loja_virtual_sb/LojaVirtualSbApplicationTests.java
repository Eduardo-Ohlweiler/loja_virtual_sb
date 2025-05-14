package com.loja_virtual_sb;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loja_virtual_sb.controller.AcessoController;
import com.loja_virtual_sb.model.Acesso;
import com.loja_virtual_sb.repository.AcessoRepository;

@SpringBootTest
@AutoConfigureMockMvc
class LojaVirtualSbApplicationTests {

	@Autowired
	private AcessoController acessoController;

	@Autowired
	private AcessoRepository acessoRepository;
	
	@Autowired
	private WebApplicationContext wac;

	@Test
	public void testRestApiCadastroAcesso() throws JsonProcessingException, Exception {

		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);

		MockMvc mockMvc = builder.build();

		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_COMPRADOR");

		ObjectMapper objectMapper = new ObjectMapper();

		ResultActions retornoApi = mockMvc
			.perform(MockMvcRequestBuilders.post("/salvarAcesso")
			.content(objectMapper.writeValueAsString(acesso))
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON));

		Acesso objetoRetorno = objectMapper.
							   readValue(retornoApi.andReturn().getResponse().getContentAsString(),
							   Acesso.class);
		assertEquals(acesso.getDescricao(), objetoRetorno.getDescricao());
	}

	@Test
	void testCadastraAcesso() {

		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_ADMIN");
		acesso = acessoController.salvarAcesso(acesso).getBody();

		assertTrue(acesso.getId() > 0);
		assertEquals("ROLE_ADMIN", acesso.getDescricao());

		Acesso acesso2 = acessoRepository.findById(acesso.getId()).get();
		assertEquals(acesso.getId(), acesso2.getId());

		acessoRepository.deleteById(acesso2.getId());
		acessoRepository.flush();

		Acesso acesso3 = acessoRepository.findById(acesso2.getId()).orElse(null);
		assertNull(acesso3);

		acesso = new Acesso();
		acesso.setDescricao("ROLE_ALUNO");
		acesso = acessoController.salvarAcesso(acesso).getBody();

		List<Acesso> acessos = acessoRepository.buscarAcessoDesc("ALUNO".trim());
		assertEquals(1, acessos.size());
		acessoRepository.deleteById(acesso.getId());
	}
}
