package com.borajunto.projetobora.entidade;

// Importações das anotações e classes necessárias do Jakarta Persistence e Lombok
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Anotação JPA que define esta classe como uma entidade persistente
@Entity
// Anotação que especifica o nome da tabela correspondente no banco de dados
@Table(name = "motorista")

// Lombok: Gera automaticamente os métodos getter e setter para todos os campos da classe
@Getter
@Setter
// Lombok: Gera um construtor padrão (sem argumentos)
@NoArgsConstructor
public class Motorista extends Usuario {  // Extende a classe Usuario, possivelmente compartilhando atributos comuns a um usuário

    // Define o campo id como chave primária da entidade
    @Id
    private int id;
    
    // Campo que armazena a CNH (Carteira Nacional de Habilitação) do motorista
    private String cnh;
}
