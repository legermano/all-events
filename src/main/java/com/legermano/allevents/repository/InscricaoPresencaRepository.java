package com.legermano.allevents.repository;

import java.util.Optional;

import com.legermano.allevents.model.Inscricao;
import com.legermano.allevents.model.InscricaoPresenca;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InscricaoPresencaRepository extends JpaRepository<InscricaoPresenca, Integer>{
    Optional<InscricaoPresenca> findByInscricao(Inscricao inscricao);
}
