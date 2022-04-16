package com.legermano.allevents.repository;

import java.util.Optional;

import com.legermano.allevents.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    public Optional<Usuario> findByEmail(String email);
}
