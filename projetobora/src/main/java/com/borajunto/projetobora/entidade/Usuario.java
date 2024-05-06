package com.borajunto.projetobora.entidade;

import java.util.Objects;

import com.borajunto.projetobora.dto.UsuarioDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

//Anotações do Spring Boot. 

//Dizendo pro spring que isso se trata de uma entidade
@Entity

//Estamos mapeando nossa classe, dizendo pro Spring que essa entidade vai ter uma tabela chamada bjt_usuario no banco de dados
@Table(name = "BJT_USUARIO")

//Importamos o lombok, assim podemos fazer a anotação de getters e setters, não sendo mais necessário criar todos os métodos à mão. Pois toda vez que instanciarmos um objeto teremos os métodos get e set prontos
@Getter
@Setter


//CAMADA ENTITY(ENTIDADE). Aqui (no pacote entidade) criamos as principais entidades do nosso sistema, por ex: Entidade Usuário, Entidade Caronas, etc..
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

    public Usuario() {}

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
