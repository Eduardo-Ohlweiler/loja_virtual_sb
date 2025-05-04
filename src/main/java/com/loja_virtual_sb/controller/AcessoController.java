package com.loja_virtual_sb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.loja_virtual_sb.model.Acesso;
import com.loja_virtual_sb.service.AcessoService;

@Controller
public class AcessoController {
    
    @Autowired
    private AcessoService acessoService;

    @PostMapping("/salvarAcesso")
    public Acesso salvarAcesso(Acesso acesso) {
        return acessoService.save(acesso);
    }
}
