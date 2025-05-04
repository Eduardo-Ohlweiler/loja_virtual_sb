package com.loja_virtual_sb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.loja_virtual_sb.model.Acesso;

@Repository
@Transactional
public interface AcessoRepository extends JpaRepository<Acesso, Long> {
    
    @Query("SELECT a FROM Acesso a WHERE trim(a.descricao) ilike %?1%")
    List<Acesso> buscarAcessoDesc(String descricao);
}
