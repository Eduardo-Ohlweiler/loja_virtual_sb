package com.loja_virtual_sb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.loja_virtual_sb.model.Acesso;
import com.loja_virtual_sb.repository.AcessoRepository;
import com.loja_virtual_sb.service.AcessoService;

@Controller
@RestController
public class AcessoController {
    
    @Autowired
    private AcessoService acessoService;

    @Autowired
    private AcessoRepository acessoRepository;

    @ResponseBody
    @PostMapping(value = "/salvarAcesso")
    public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso)
    {
        Acesso acessoSalvo = acessoService.save(acesso);
        return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "/deleteAcesso")
    public ResponseEntity<?> deleteAcesso(@RequestBody Acesso acesso)
    {
        acessoRepository.deleteById(acesso.getId());
        return new ResponseEntity("Acesso removido",HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "/deleteAcessoPorId")
    public ResponseEntity<?> deleteAcessoPorId(@PathVariable("id") Long id)
    {
        acessoRepository.deleteById(id);
        return new ResponseEntity("Acesso removido",HttpStatus.OK);
    }
}
