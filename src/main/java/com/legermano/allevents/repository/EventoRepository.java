package com.legermano.allevents.repository;

import com.legermano.allevents.model.Evento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer>{
    
}
