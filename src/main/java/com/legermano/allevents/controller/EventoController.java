package com.legermano.allevents.controller;

import java.util.List;
import java.util.Optional;

import com.legermano.allevents.exception.ApiRequestException;
import com.legermano.allevents.helper.CriteriaParse;
import com.legermano.allevents.helper.GenericSpecification;
import com.legermano.allevents.helper.GenericSpecificationsBuilder;
import com.legermano.allevents.model.Evento;
import com.legermano.allevents.repository.EventoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public List<Evento> search(@RequestParam(value = "filter") String filter) {
        CriteriaParse parser = new CriteriaParse();
        GenericSpecificationsBuilder<Evento> specBuilder = new GenericSpecificationsBuilder<>();
        Specification<Evento> spec = specBuilder.build(parser.parse(filter), GenericSpecification<Evento>::new);
        return eventoRepository.findAll(spec);
    }
}
