package com.borajunto.projetobora.dto;

import com.borajunto.projetobora.entidade.Usuario;

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

    public UsuarioDTO(Usuario usuario) {
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRA() {
        return rA;
    }

    public void setRA(String rA) {
        this.rA = rA;
    }

    public String getUniversidade() {
        return universidade;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
