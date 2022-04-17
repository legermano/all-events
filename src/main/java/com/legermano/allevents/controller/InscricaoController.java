package com.legermano.allevents.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.legermano.allevents.exception.ApiRequestException;
import com.legermano.allevents.model.Evento;
import com.legermano.allevents.model.Inscricao;
import com.legermano.allevents.model.Usuario;
import com.legermano.allevents.repository.EventoRepository;
import com.legermano.allevents.repository.InscricaoRepository;
import com.legermano.allevents.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/inscricao")
public class InscricaoController {
    @Autowired
    InscricaoRepository inscricaoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EventoRepository eventoRepository;
    
    @PostMapping(value="/registrar")
    public Inscricao register(@RequestBody Map<String, String> inscricaoMap) {
        if(!inscricaoMap.containsKey("ref_evento") || !inscricaoMap.containsKey("ref_usuario")) {
            throw new ApiRequestException("Necessário especificar todos os campos", HttpStatus.BAD_REQUEST);
        }

        Optional<Usuario> usuario = usuarioRepository.findById(Integer.parseInt(inscricaoMap.get("ref_usuario")));
        if(usuario.isEmpty()) {
            throw new ApiRequestException("Usuário não encontrado", HttpStatus.BAD_REQUEST);
        }

        Optional<Evento> evento = eventoRepository.findById(Integer.parseInt(inscricaoMap.get("ref_evento")));
        if(evento.isEmpty()) {
            throw new ApiRequestException("Evento não encontrado", HttpStatus.BAD_REQUEST);
        }

        List<Inscricao> inscricoes = inscricaoRepository.findByUsuarioAndEventoAndDataCancelamentoNull(usuario.get(), evento.get());

        if(!inscricoes.isEmpty()) {
            throw new ApiRequestException("Usuário já possui uma inscrição ativa para o evento", HttpStatus.BAD_REQUEST);
        }

        Inscricao inscricao = new Inscricao();
        inscricao.setUsuario(usuario.get());
        inscricao.setEvento(evento.get());
        inscricao.setDataInscricao(LocalDateTime.now());
        
        return inscricaoRepository.save(inscricao);
    }

    @PostMapping(value = "/cancelar/{id}")
    public Inscricao cancel(@PathVariable String id) {
        Optional<Inscricao> inscricaoOptional = inscricaoRepository.findById(Integer.parseInt(id));

        if(inscricaoOptional.isEmpty()) {
            throw new ApiRequestException("Inscrição não encontrada", HttpStatus.NOT_FOUND);
        }

        Inscricao inscricao = inscricaoOptional.get();

        if(inscricao.getDataCancelamento() == null) {
            // Verifica se a data atual está dentro dos dois 2 dias de cancelamento
            LocalDateTime dataMaximaCancelamento = inscricao.getDataInscricao().plusDays(2);
            LocalDateTime dataAtual = LocalDateTime.now();

            if(dataAtual.isAfter(dataMaximaCancelamento)) {
                throw new ApiRequestException("Não é possível cancelar a inscrição após 2 dias", HttpStatus.BAD_REQUEST);
            }
        
            inscricao.setDataCancelamento(dataAtual);
            inscricao = inscricaoRepository.save(inscricao);
        }

        return inscricao;
    }
    
}
