package com.legermano.allevents.controller;

import java.util.Optional;

import com.legermano.allevents.exception.ApiRequestException;
import com.legermano.allevents.model.InscricaoCertificado;
import com.legermano.allevents.repository.InscricaoCertificadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inscricao/certificado")
public class InscricaoCertificadoController {
    @Autowired
    InscricaoCertificadoRepository certificadoRepository;
    
    @GetMapping("/validar/{codigo}")
    public InscricaoCertificado validate(@PathVariable String codigo) {
        Optional<InscricaoCertificado> certificado = certificadoRepository.findByCodigo(codigo);

        if(certificado.isEmpty()) {
            throw new ApiRequestException("Certificado não encontrado para o código informado", HttpStatus.NOT_FOUND);
        }
        
        return certificado.get();
    }
}
