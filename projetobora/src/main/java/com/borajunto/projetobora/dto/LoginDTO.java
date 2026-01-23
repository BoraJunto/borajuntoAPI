package com.borajunto.projetobora.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    private String login;
    private String senha;

    public LoginDTO() {}

    public LoginDTO(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }
}
