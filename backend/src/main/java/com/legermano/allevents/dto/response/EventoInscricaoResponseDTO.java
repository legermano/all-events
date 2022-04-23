package com.legermano.allevents.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EventoInscricaoResponseDTO {
    private Integer id;
    private UsuarioResponseDTO usuario;
    private LocalDateTime dataInscricao;
    private LocalDateTime dataCancelamento;
}
