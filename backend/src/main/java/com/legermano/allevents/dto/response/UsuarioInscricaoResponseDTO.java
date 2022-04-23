package com.legermano.allevents.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UsuarioInscricaoResponseDTO {
    private Integer id;
    private EventoResponseDTO evento;
    private LocalDateTime dataInscricao;
    private LocalDateTime dataCancelamento;
    private LocalDateTime dataPresenca;
}
