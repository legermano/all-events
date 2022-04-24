package com.legermano.allevents.controller;

import java.util.Map;
import java.util.Optional;

import com.legermano.allevents.dto.response.UsuarioResponseDTO;
import com.legermano.allevents.exception.ApiRequestException;
import com.legermano.allevents.model.Usuario;
import com.legermano.allevents.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {
    @Autowired
    UsuarioRepository usuarioController;

    @PostMapping(value = "/usuario")
    public UsuarioResponseDTO autenticaUsuario(@RequestBody Map<String, String> usuarioMap) {
        if(!usuarioMap.containsKey("email") || !usuarioMap.containsKey("senha")) {
            throw new ApiRequestException("Necessário especificar todos os campos", HttpStatus.BAD_REQUEST);
        }

        Boolean autenticado = false;

        Optional<Usuario> usuario = usuarioController.findByEmail(usuarioMap.get("email"));

        if(usuario.isPresent()) {
            autenticado = usuario.get().validaSenha(usuarioMap.get("senha"));
        }

        if(!autenticado) {
            throw new ApiRequestException("Usuário e/ou senha incorretos", HttpStatus.BAD_REQUEST);
        }

        return usuario.get().paraUsuarioResponseDTO();
    }
}
