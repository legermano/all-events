package com.legermano.allevents.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EventoResponseDTO {
    private Integer id;
    private String nome;
    private String descricao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Integer maximoVagas;
}
