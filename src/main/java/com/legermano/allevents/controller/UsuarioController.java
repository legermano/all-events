package com.legermano.allevents.controller;

import java.util.List;

import com.legermano.allevents.model.Usuario;
import com.legermano.allevents.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping
    public Usuario listAll(){
        return usuarioRepository.findById(1).get();
    }

    @PostMapping
    public Usuario save(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }
}
