package com.legermano.allevents.repository;

import java.util.List;

import com.legermano.allevents.model.Evento;
import com.legermano.allevents.model.Inscricao;
import com.legermano.allevents.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Integer>{
    List<Inscricao> findByUsuarioAndEvento(Usuario usuario, Evento evento);
    List<Inscricao> findByUsuarioAndEventoAndDataCancelamentoNull(Usuario usuario, Evento evento);
    List<Inscricao> findByCodigoEventoAndDataCancelamentoNull(Integer evento);
    List<Inscricao> findByCodigoUsuarioAndDataCancelamentoNull(Integer usuario);
    List<Inscricao> findByCodigoUsuario(Integer usuario);
}
