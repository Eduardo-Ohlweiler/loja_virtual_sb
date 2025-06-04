package com.loja_virtual_sb.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Filtra onde todas as rquisições serão capturadas
public class JWTApiAutenticacaoFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        //estabelece a autenticação do user
        Authentication authentication = new JWTTokenAutenticacaoService().getAuthentication((HttpServletRequest)request, (HttpServletResponse)response);

        //Coloca o processo de autenticação para o spring security
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
        
        throw new UnsupportedOperationException("Unimplemented method 'doFilter'");
    }
    
}
