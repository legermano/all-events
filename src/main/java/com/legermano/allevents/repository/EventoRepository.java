package com.legermano.allevents.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.legermano.allevents.model.Evento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer>, JpaSpecificationExecutor<Evento>{
    List<Evento> findByDataFimGreaterThan(LocalDateTime dataInicio);

    List<Evento> findByDataInicioLessThanEqualAndDataFimGreaterThan(LocalDateTime dataInicio, LocalDateTime dataFim);
}
