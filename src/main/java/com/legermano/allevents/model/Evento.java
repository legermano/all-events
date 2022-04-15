package com.legermano.allevents.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    
    @Column(name = "dt_inicio", nullable = false)
    private Timestamp dataInicio;

    @Column(name = "dt_fim", nullable = false)
    private Timestamp dataFim;

    @Column(name = "maximo_vagas")
    private Integer maximoVagas;

    @JsonManagedReference(value = "inscricao_ref_evento")
    @OneToMany(mappedBy = "evento")
    private List<Inscricao> inscricoes;
}
