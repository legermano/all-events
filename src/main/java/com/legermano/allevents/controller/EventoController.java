package com.legermano.allevents.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.legermano.allevents.exception.ApiRequestException;
import com.legermano.allevents.model.Evento;
import com.legermano.allevents.repository.EventoRepository;
import com.legermano.allevents.util.DateUtils;
import com.legermano.allevents.util.genericSearch.GenericSpecification;
import com.legermano.allevents.util.genericSearch.GenericSpecificationsBuilder;
import com.legermano.allevents.util.genericSearch.SearchCriteriaList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/evento")
public class EventoController {
    @Autowired
    EventoRepository eventoRepository;

    @GetMapping("/{id}")
    public Evento getEvento(@PathVariable String id) {
        Optional<Evento> evento = eventoRepository.findById(Integer.parseInt(id));

        if(evento.isEmpty()) {
            throw new ApiRequestException("Evento não encontrado", HttpStatus.NOT_FOUND);
        }

        return evento.get();
    }    

    @GetMapping(value = "/todos")
    public List<Evento> getAll() {
        return eventoRepository.findAll();
    }
    
    @GetMapping(value = "/filtro")
    public List<Evento> search(@RequestBody SearchCriteriaList search) {
        GenericSpecificationsBuilder<Evento> specBuilder = new GenericSpecificationsBuilder<>();
        Specification<Evento> spec = specBuilder.build(search.parse(), GenericSpecification<Evento>::new);
        return eventoRepository.findAll(spec);
    }

    @GetMapping(value = "/futuro")
    public List<Evento> getFuture(@RequestBody Map<String, String> eventoMap) {
        LocalDateTime dateTime = DateUtils.convertStrinToLocalDateTime(eventoMap.get("date"));
        return eventoRepository.findByDataFimGreaterThan(dateTime);

    }

    @GetMapping(value = "/ativos")
    public List<Evento> getActive(@RequestBody Map<String, String> eventoMap) {
        LocalDateTime dateTime = DateUtils.convertStrinToLocalDateTime(eventoMap.get("date"));
        return eventoRepository.findByDataInicioLessThanEqualAndDataFimGreaterThan(dateTime, dateTime);
    }
}
