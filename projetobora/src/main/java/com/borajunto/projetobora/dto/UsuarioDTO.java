package com.borajunto.projetobora.dto;

import com.borajunto.projetobora.entidade.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private String cpf;
    private String rA;
    private String universidade;
    private String nome;
    private String login;
    private String senha;
    private String email;
    private String telefone;
    private String sexo;

    public UsuarioDTO() {
    }

    public UsuarioDTO(UsuarioDTO usuarioDTO) {
        this.cpf = usuarioDTO.getCpf();
        this.rA = usuarioDTO.getRA();
        this.universidade = usuarioDTO.getUniversidade();
        this.nome = usuarioDTO.getNome();
        this.login = usuarioDTO.getLogin();
        this.senha = usuarioDTO.getSenha();
        this.email = usuarioDTO.getEmail();
        this.telefone = usuarioDTO.getTelefone();
        this.sexo = usuarioDTO.getSexo();
    }

    public UsuarioDTO(Usuario usuario){
        this.cpf = usuario.getCpf();
        this.rA = usuario.getRA();
        this.universidade = usuario.getUniversidade();
        this.nome = usuario.getNome();
        this.login = usuario.getLogin();
        this.senha = usuario.getSenha();
        this.email = usuario.getEmail();
        this.telefone = usuario.getTelefone();
        this.sexo = usuario.getSexo();
    }
    

}
