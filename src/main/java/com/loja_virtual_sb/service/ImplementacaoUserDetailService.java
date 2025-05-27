package com.loja_virtual_sb.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.loja_virtual_sb.model.Usuario;
import com.loja_virtual_sb.repository.UsuarioRepository;

@Service
public class ImplementacaoUserDetailService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    public ImplementacaoUserDetailService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findUserByLogin(username);
        
        if(usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return new User(usuario.getLogin(), usuario.getPassword(), usuario.getAuthorities());
    }
    
}
