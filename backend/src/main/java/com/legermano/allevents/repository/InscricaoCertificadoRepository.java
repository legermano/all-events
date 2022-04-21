package com.legermano.allevents.repository;

import java.util.Optional;

import com.legermano.allevents.model.InscricaoCertificado;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InscricaoCertificadoRepository extends JpaRepository<InscricaoCertificado, Integer>{
    Optional<InscricaoCertificado> findByCodigo(String codigo);
}
