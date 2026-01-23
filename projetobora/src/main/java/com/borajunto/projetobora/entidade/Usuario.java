package com.borajunto.projetobora.entidade;

import java.util.Objects;

import com.borajunto.projetobora.dto.UsuarioDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "BJT_USUARIO")
public class Usuario {

    @Id
    private String cpf;
    private String rA;
    private String universidade;
    private String nome;
    private String login;
    private String senha;
    private String email;
    private String telefone;
    private String sexo;

    public Usuario() {
    }

    public Usuario(UsuarioDTO usuarioDTO) {
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

    // Getters and Setters
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

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Usuario other = (Usuario) obj;
        return Objects.equals(this.cpf, other.cpf);
    }
}
