package com.legermano.allevents.model;

import java.time.LocalDateTime;
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
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.legermano.allevents.dto.response.EventoInscricaoResponseDTO;
import com.legermano.allevents.dto.response.UsuarioInscricaoResponseDTO;
import com.legermano.allevents.util.DateUtils;

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

    @Column(name = "ref_usuario", insertable = false, updatable = false)
    private Integer codigoUsuario;

    @JsonBackReference(value = "inscricao_ref_usuario")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ref_usuario", referencedColumnName = "id", nullable = false)
    private Usuario usuario;

    @Column(name = "ref_evento", insertable = false, updatable = false)
    private Integer codigoEvento;

    @JsonBackReference(value = "inscricao_ref_evento")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_evento", referencedColumnName = "id", nullable = false)
    private Evento evento;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DEFAULT_DATE_PATTERN)
    @Column(name = "dt_inscricao", nullable = false)
    private LocalDateTime dataInscricao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DEFAULT_DATE_PATTERN)
    @Column(name = "dt_cancelamento")
    private LocalDateTime dataCancelamento;

    @JsonManagedReference(value = "inscricao_presenca_ref_inscricao")
    @OneToMany(mappedBy = "inscricao")
    private List<InscricaoPresenca> inscricoesPresencas;

    @JsonManagedReference(value = "inscricao_certificado_ref_inscricao")
    @OneToMany(mappedBy = "inscricao")
    private List<InscricaoCertificado> inscricoesCertificados;

    public EventoInscricaoResponseDTO paraEventoInscricaoResponseDTO() {
        EventoInscricaoResponseDTO dto = new EventoInscricaoResponseDTO();
        dto.setId(this.getId());
        dto.setUsuario(this.getUsuario().paraUsuarioResponseDTO());
        dto.setDataInscricao(this.getDataInscricao());
        dto.setDataCancelamento(this.getDataCancelamento());

        return dto;
    }

    public UsuarioInscricaoResponseDTO paraUsuarioInscricaoResponseDTO() {
        UsuarioInscricaoResponseDTO dto = new UsuarioInscricaoResponseDTO();
        dto.setId(this.getId());
        dto.setEvento(this.getEvento().paraEventoResponseDTO());
        dto.setDataInscricao(this.getDataInscricao());
        dto.setDataCancelamento(this.getDataCancelamento());

        return dto;
    }
}
