package com.legermano.allevents.dto.receiver;

import java.util.Optional;

import com.legermano.allevents.model.Usuario;
import com.legermano.allevents.repository.UsuarioRepository;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDTO {
    private Integer id;
    private String nome;
    private String endereco;
    private String telefone;
    private String cpf;
    private String email;
    private String senha;

    public Usuario paraUsuario(UsuarioRepository usuarioRepository)
    {
        Usuario usuario = null;

        // Se for atualização, deve buscar o usuário do banco para não limpar os campos que não foram passados no POST
        if(this.getId() != null)
        {
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(this.getId());

            if(usuarioOptional.isPresent()) {
                usuario = usuarioOptional.get();
            }
        }

        if(usuario == null) {
            usuario = new Usuario();
        }

        usuario.setNome(this.getNome());
        usuario.setEndereco(this.getEndereco());
        usuario.setTelefone(this.getTelefone());
        usuario.setCpf(this.getCpf());
        usuario.setEmail(this.getEmail());

        if(this.getSenha() != null) {
            usuario.setSenha(this.getSenha());
        }

        return usuario;
    }
}
