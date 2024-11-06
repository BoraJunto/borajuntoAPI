package com.borajunto.projetobora.service;

// Importações das bibliotecas necessárias
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Importação de classes específicas do projeto
import com.borajunto.projetobora.entidade.Passageiro;
import com.borajunto.projetobora.exception.EmailAlreadyExistsException;
import com.borajunto.projetobora.repository.PassageiroRepository;

// Anotação que define a classe como um serviço Spring
@Service
public class PassageiroService {

    // Injeta automaticamente a dependência PassageiroRepository para acesso aos dados do passageiro
    @Autowired
    private PassageiroRepository passageiroRepository;

    /**
     * Método para inserir um novo passageiro no banco de dados.
     * Verifica se o email já existe antes de inserir.
     * 
     * @param passageiroEntity objeto Passageiro a ser inserido
     * @throws EmailAlreadyExistsException caso o email já esteja registrado
     */
    @Transactional  // Garante que as operações sejam tratadas como transações atômicas
    public void inserirPassageiro(Passageiro passageiroEntity) {
        String email = passageiroEntity.getEmail();
        Boolean emailExiste = verificarEmail(email);

        // Se o email não existe, salva o passageiro; caso contrário, lança exceção
        if (!emailExiste) {
            passageiroRepository.save(passageiroEntity);
        } else {
            throw new EmailAlreadyExistsException("Esse email já existe em nosso banco de dados");
        }
    }

    /**
     * Método para listar todos os passageiros.
     * 
     * @return List<Passageiro> lista contendo todos os passageiros registrados
     */
    public List<Passageiro> listaPassageiros() {
        return passageiroRepository.findAll();
    }

    /**
     * Método para excluir um passageiro pelo ID.
     * 
     * @param id identificador do passageiro a ser excluído
     */
    public void excluirPassageiro(int id) {
        passageiroRepository.deleteById(id);
    }

    /**
     * Método para verificar se um email já existe no banco de dados.
     * 
     * @param email email a ser verificado
     * @return boolean true se o email já existe, false caso contrário
     */
    public boolean verificarEmail(String email) {
        return passageiroRepository.existsByEmail(email);
    }

    /**
     * Método de autenticação de login.
     * 
     * @param email email do passageiro para autenticação
     * @param senha senha do passageiro para autenticação
     * @return boolean true se o email e senha são válidos, false caso contrário
     */
    public boolean login(String email, String senha) {
        Optional<Passageiro> optionalPassageiro = passageiroRepository.findByEmail(email);

        // Verifica se o passageiro existe e se a senha corresponde
        if (optionalPassageiro.isPresent()) {
            Passageiro passageiro = optionalPassageiro.get();
            return passageiro.getSenha().equals(senha);
        }
        return false;
    }
}
