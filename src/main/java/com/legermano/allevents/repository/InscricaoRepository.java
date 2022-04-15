package com.legermano.allevents.repository;

import com.legermano.allevents.model.Inscricao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Integer>{
    
}
