package com.legermano.allevents.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.legermano.allevents.util.DateUtils;

import lombok.Data;

@Data
public class EventoResponseDTO {
    private Integer id;
    private String nome;
    private String descricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DEFAULT_DATE_PATTERN)
    private LocalDateTime dataInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DEFAULT_DATE_PATTERN)
    private LocalDateTime dataFim;
    private Integer maximoVagas;
    private List<EventoInscricaoResponseDTO> inscricoes;
}
