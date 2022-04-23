package com.legermano.allevents.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class InscricaoResponseDTO {
    private Integer id;
    private UsuarioResponseDTO usuario;
    private EventoResponseDTO evento;
    private LocalDateTime dataInscricao;
    private LocalDateTime dataCancelamento;
    private LocalDateTime dataPresenca;
}
