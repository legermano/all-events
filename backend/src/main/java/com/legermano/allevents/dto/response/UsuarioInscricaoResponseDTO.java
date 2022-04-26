package com.legermano.allevents.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.legermano.allevents.util.DateUtils;

import lombok.Data;

@Data
public class UsuarioInscricaoResponseDTO {
    private Integer id;
    private EventoResponseDTO evento;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DEFAULT_DATE_PATTERN)
    private LocalDateTime dataInscricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DEFAULT_DATE_PATTERN)
    private LocalDateTime dataCancelamento;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DEFAULT_DATE_PATTERN)
    private LocalDateTime dataPresenca;
}
