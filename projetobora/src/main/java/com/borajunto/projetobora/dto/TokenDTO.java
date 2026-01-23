package com.borajunto.projetobora.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDTO {
    private String token;
    private String type = "Bearer";
    private Long expiresIn;

    public TokenDTO() {}

    public TokenDTO(String token, Long expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }
}
