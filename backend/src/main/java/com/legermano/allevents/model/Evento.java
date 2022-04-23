package com.legermano.allevents.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.legermano.allevents.dto.response.EventoResponseDTO;
import com.legermano.allevents.util.DateUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DEFAULT_DATE_PATTERN)
    @Column(name = "dt_inicio", nullable = false)
    private LocalDateTime dataInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DEFAULT_DATE_PATTERN)
    @Column(name = "dt_fim", nullable = false)
    private LocalDateTime dataFim;

    @Column(name = "maximo_vagas")
    private Integer maximoVagas;

    @JsonManagedReference(value = "inscricao_ref_evento")
    @OneToMany(mappedBy = "evento")
    private List<Inscricao> inscricoes;

    public EventoResponseDTO paraEventoResponseDTO() {
        EventoResponseDTO dto = new EventoResponseDTO();
        dto.setId(this.getId());
        dto.setNome(this.getNome());
        dto.setDescricao(this.getDescricao());
        dto.setDataInicio(this.getDataInicio());
        dto.setDataFim(this.getDataFim());
        dto.setMaximoVagas(this.getMaximoVagas());

        return dto;
    }
}
