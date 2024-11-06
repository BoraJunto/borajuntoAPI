package com.borajunto.projetobora.controler;

// Importação das bibliotecas necessárias
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Importação de classes específicas do projeto
import com.borajunto.projetobora.entidade.Passageiro;
import com.borajunto.projetobora.exception.EmailAlreadyExistsException;
import com.borajunto.projetobora.service.PassageiroService;

// Anotação que define a classe como um controlador REST
@RestController
@RequestMapping("/api/passageiro")  // Mapeamento base para todas as rotas
@CrossOrigin(origins = "http://localhost:")  // Permite o acesso de origem cruzada para a URL especificada
public class PassageiroController {

    // Injeta automaticamente a dependência PassageiroService, responsável pela lógica de negócios dos passageiros
    @Autowired
    PassageiroService passageiroService;

    /**
     * Endpoint para inserir um novo passageiro.
     * 
     * @param passageiroEntity objeto Passageiro a ser inserido, recebido no corpo da requisição
     * @return ResponseEntity<?> retorna o passageiro criado com status HTTP 201 (CREATED) ou, 
     *         em caso de erro de email duplicado, retorna o erro com status HTTP 409 (CONFLICT)
     */
    @PostMapping
    public ResponseEntity<?> inserirPassageiro(@Validated @RequestBody Passageiro passageiroEntity) {
        try {
            // Chama o serviço para inserir o passageiro
            passageiroService.inserirPassageiro(passageiroEntity);
            return new ResponseEntity<>(passageiroEntity, HttpStatus.CREATED);
        } catch (EmailAlreadyExistsException e) {
            // Trata exceção de email duplicado e retorna a mensagem de erro com status 409
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    /**
     * Endpoint para listar todos os passageiros.
     * 
     * @return ResponseEntity<List<Passageiro>> retorna uma lista de passageiros com status HTTP 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<Passageiro>> listaPassageiros(){
        List<Passageiro> passageiros = passageiroService.listaPassageiros();
        return new ResponseEntity<>(passageiros, HttpStatus.OK);
    }

    /**
     * Endpoint para excluir um passageiro pelo ID.
     * 
     * @param id identificador do passageiro a ser excluído, fornecido como variável de caminho
     * @return ResponseEntity<Void> retorna status HTTP 204 (NO CONTENT) se a exclusão for bem-sucedida
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPassageiro(@PathVariable int id){
        passageiroService.excluirPassageiro(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para autenticação de login de um passageiro.
     * 
     * @param passageiro objeto Passageiro contendo email e senha para autenticação
     * @return ResponseEntity<String> retorna mensagem de sucesso com status 200 (OK) se autenticado,
     *         ou mensagem de erro com status 401 (UNAUTHORIZED) caso as credenciais sejam inválidas
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Passageiro passageiro) {
        // Verifica a autenticidade do email e senha do passageiro
        boolean autenticado = passageiroService.login(passageiro.getEmail(), passageiro.getSenha());

        if(autenticado){
            return ResponseEntity.ok("Login autorizado!");
        }

        // Retorna erro 401 se a autenticação falhar
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha inválidos");
    }
}
