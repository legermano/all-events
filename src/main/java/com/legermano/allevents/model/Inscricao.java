package com.legermano.allevents.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inscricao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonBackReference(value = "inscricao_ref_usuario")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_usuario", referencedColumnName = "id", nullable = false)
    private Usuario usuario;
    
    @JsonBackReference(value = "inscricao_ref_evento")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ref_evento", referencedColumnName = "id", nullable = false)
    private Evento evento;

    @Column(name = "dt_inscricao", nullable = false)
    private Timestamp dataInscricao;

    @Column(name = "dt_cancelamento")
    private Timestamp dataCancelamento;

    @JsonManagedReference(value = "inscricao_presenca_ref_inscricao")
    @OneToMany(mappedBy = "inscricao")
    private List<InscricaoPresenca> inscricoesPresencas;
    
    @JsonManagedReference(value = "inscricao_certificado_ref_inscricao")
    @OneToMany(mappedBy = "inscricao")
    private List<InscricaoCertificado> inscricoesCertificados;
}
