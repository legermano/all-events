package com.legermano.allevents.dto.response;

import lombok.Data;

@Data
public class UsuarioResponseDTO {
    private Integer id;
    private String nome;
    private String endereco;
    private String telefone;
    private String cpf;
    private String email;
}
