package com.legermano.allevents.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.legermano.allevents.dto.response.EventoInscricaoResponseDTO;
import com.legermano.allevents.exception.ApiRequestException;
import com.legermano.allevents.model.Evento;
import com.legermano.allevents.model.Inscricao;
import com.legermano.allevents.repository.EventoRepository;
import com.legermano.allevents.repository.InscricaoRepository;
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

    @Autowired
    InscricaoRepository inscricaoRepository;

    @GetMapping("/{id}")
    public Evento getEvento(@PathVariable Integer id) {
        Optional<Evento> evento = eventoRepository.findById(id);

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
    public List<Evento> getFuture(@RequestBody(required = false) Map<String, String> eventoMap) {
        // Inicia com a data e hora atual para filtrar os eventos
        LocalDateTime dateTime = DateUtils.getCurrentDateTime();

        // Se for passado uma data no body da requisição, filtra por ela
        if(eventoMap != null && eventoMap.containsKey("date"))
        {
            dateTime = DateUtils.convertStrinToLocalDateTime(eventoMap.get("date"));
        }

        return eventoRepository.findByDataFimGreaterThan(dateTime);
    }

    @GetMapping(value = "/ativos")
    public List<Evento> getActive(@RequestBody(required = false) Map<String, String> eventoMap) {
        // Inicia com a data e hora atual para filtrar os eventos
        LocalDateTime dateTime = DateUtils.getCurrentDateTime();

        // Se for passado uma data no body da requisição, filtra por ela
        if(eventoMap != null && eventoMap.containsKey("date"))
        {
            dateTime = DateUtils.convertStrinToLocalDateTime(eventoMap.get("date"));
        }

        return eventoRepository.findByDataInicioLessThanEqualAndDataFimGreaterThan(dateTime, dateTime);
    }

    @GetMapping(value = "{codigoEvento}/inscricoes")
    public List<EventoInscricaoResponseDTO> getInscricoes(@PathVariable Integer codigoEvento) {
        return inscricaoRepository
               .findByCodigoEventoAndDataCancelamentoNull(codigoEvento)
               .stream()
               .map(Inscricao::paraEventoInscricaoResponseDTO)
               .collect(Collectors.toList());
    }
}
