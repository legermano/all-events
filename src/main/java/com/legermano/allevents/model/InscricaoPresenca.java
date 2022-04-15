package com.legermano.allevents.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InscricaoPresenca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonBackReference(value = "inscricao_presenca_ref_inscricao")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_inscricao", referencedColumnName = "id", nullable = false)
    private Inscricao inscricao;

    @Column(name = "dt_presenca", nullable = false)
    private Timestamp dataPresenca;

    private String observacao;
}