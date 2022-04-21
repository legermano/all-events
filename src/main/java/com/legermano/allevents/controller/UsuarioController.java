package com.legermano.allevents.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.legermano.allevents.dto.receiver.UsuarioDTO;
import com.legermano.allevents.dto.response.UsuarioResponseDTO;
import com.legermano.allevents.exception.ApiRequestException;
import com.legermano.allevents.model.Usuario;
import com.legermano.allevents.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping()
    public Usuario getUsuario(@RequestBody Map<String, String> usuarioMap) {
        if(!usuarioMap.containsKey("id") && !usuarioMap.containsKey("email")) {
            throw new ApiRequestException("Necessário especificar o código do usuário e o seu e-mail", HttpStatus.BAD_REQUEST);
        }

        Optional<Usuario> usuario;

        if(usuarioMap.containsKey("id")) {
            usuario = usuarioRepository.findById(Integer.valueOf(usuarioMap.get("id")));
        } else {
            usuario = usuarioRepository.findByEmail(usuarioMap.get("email"));
        }

        if(usuario.isEmpty()) {
            throw new ApiRequestException("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }

        return usuario.get();
    }

    @GetMapping(value = "/todos")
    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UsuarioResponseDTO save(@RequestBody UsuarioDTO usuario) {
        return  usuarioRepository.save(usuario.paraUsuario()).paraUsuarioResponseDTO();
    }
}
