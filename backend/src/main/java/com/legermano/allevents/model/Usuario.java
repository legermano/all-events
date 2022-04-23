package com.legermano.allevents.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.legermano.allevents.dto.response.UsuarioResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    private String endereco;

    private String telefone;

    @Column(unique = true, nullable = false)
    private String email;

    //TODO: Criptografar a senha no banco de dados
    private String senha;

    @Column(nullable = false)
    private Boolean fl_admin = false;

    @JsonManagedReference(value = "inscricao_ref_usuario")
    @OneToMany(mappedBy = "usuario")
    private List<Inscricao> inscricoes;

    //TODO: Quando a senha for criptografada, alterar forma de verificação
    public Boolean validaSenha(String senha) {
        return this.senha.equals(senha);
    }

    public UsuarioResponseDTO paraUsuarioResponseDTO() {
        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();
        usuarioResponseDTO.setId(this.getId());
        usuarioResponseDTO.setNome(this.getNome());
        usuarioResponseDTO.setEndereco(this.getEndereco());
        usuarioResponseDTO.setTelefone(this.getTelefone());
        usuarioResponseDTO.setCpf(this.getCpf());
        usuarioResponseDTO.setEmail(this.getEmail());

        return usuarioResponseDTO;
    }
}
