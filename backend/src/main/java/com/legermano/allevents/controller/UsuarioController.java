package com.legermano.allevents.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.legermano.allevents.dto.receiver.UsuarioDTO;
import com.legermano.allevents.dto.response.UsuarioInscricaoResponseDTO;
import com.legermano.allevents.dto.response.UsuarioResponseDTO;
import com.legermano.allevents.exception.ApiRequestException;
import com.legermano.allevents.model.Inscricao;
import com.legermano.allevents.model.Usuario;
import com.legermano.allevents.repository.InscricaoRepository;
import com.legermano.allevents.repository.UsuarioRepository;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    InscricaoRepository inscricaoRepository;

    @GetMapping()
    public UsuarioResponseDTO getUsuario(@RequestBody Map<String, String> usuarioMap) {
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

        return usuario.get().paraUsuarioResponseDTO();
    }

    @GetMapping(value = "/todos")
    public List<UsuarioResponseDTO> getAll(){
        return usuarioRepository.findAll()
                                .stream()
                                .map(Usuario::paraUsuarioResponseDTO)
                                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UsuarioResponseDTO save(@RequestBody UsuarioDTO usuario) {
        try {
            return usuarioRepository.save(
                usuario.paraUsuario(usuarioRepository)
            ).paraUsuarioResponseDTO();
        } catch (ConstraintViolationException e) {
            throw new ApiRequestException("Não foi possível criar um novo usuário, pois já existe um com esse e-mail", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new ApiRequestException("Ocorreu um erro ao salvar o usuário", e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{codigoUsuario}/inscricoes")
    public List<UsuarioInscricaoResponseDTO> getInscricoes(@PathVariable Integer codigoUsuario) {
        return inscricaoRepository
               .findByCodigoUsuarioAndDataCancelamentoNull(codigoUsuario)
               .stream()
               .map(Inscricao::paraUsuarioInscricaoResponseDTO)
               .collect(Collectors.toList());
    }
}
