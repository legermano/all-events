package com.legermano.allevents.dto.receiver;

import com.legermano.allevents.model.Usuario;

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

    public Usuario paraUsuario()
    {
        Usuario usuario = new Usuario();
        usuario.setId(id);
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
