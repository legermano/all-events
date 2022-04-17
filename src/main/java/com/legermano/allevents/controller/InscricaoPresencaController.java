package com.legermano.allevents.controller;

import java.util.Optional;

import com.legermano.allevents.exception.ApiRequestException;
import com.legermano.allevents.model.Inscricao;
import com.legermano.allevents.model.InscricaoPresenca;
import com.legermano.allevents.repository.InscricaoPresencaRepository;
import com.legermano.allevents.repository.InscricaoRepository;
import com.legermano.allevents.util.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/inscricao/presenca")
public class InscricaoPresencaController {
    @Autowired
    InscricaoPresencaRepository inscricaoPresencaRepository;

    @Autowired
    InscricaoRepository inscricaoRepository;

    @PostMapping(value = "/{inscricao_id}")
    public InscricaoPresenca register(@PathVariable Integer inscricao_id) {
        Optional<Inscricao> inscricaoOptional = inscricaoRepository.findById(inscricao_id);
        Inscricao inscricao;
        if(inscricaoOptional.isEmpty()) {
            throw new ApiRequestException("Inscrição não encontrada", HttpStatus.NOT_FOUND);
        }

        inscricao = inscricaoOptional.get();

        InscricaoPresenca novaPresenca;
        Optional<InscricaoPresenca> presenca = inscricaoPresencaRepository.findByInscricao(inscricao);

        if(presenca.isPresent()) {
            novaPresenca = presenca.get();
        } else {
            if(inscricao.getDataCancelamento() != null) {
                throw new ApiRequestException("Não é possível registrar presença em uma inscrição cancelada", HttpStatus.BAD_REQUEST);
            }

            novaPresenca = new InscricaoPresenca();
            novaPresenca.setInscricao(inscricao);
            novaPresenca.setDataPresenca(DateUtils.getCurrentDateTime());
            novaPresenca = inscricaoPresencaRepository.save(novaPresenca);
        }

        return novaPresenca;
    }
}
