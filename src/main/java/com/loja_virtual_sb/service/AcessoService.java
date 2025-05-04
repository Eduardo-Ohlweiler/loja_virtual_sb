package com.loja_virtual_sb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loja_virtual_sb.model.Acesso;
import com.loja_virtual_sb.repository.AcessoRepository;

@Service
public class AcessoService {
    
    @Autowired
    private AcessoRepository acessoRepository;

    public Acesso save(Acesso acesso) {
        return acessoRepository.save(acesso);
    }
}
