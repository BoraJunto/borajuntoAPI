package com.borajunto.projetobora.controler;

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

import com.borajunto.projetobora.entidade.Passageiro;
import com.borajunto.projetobora.exception.EmailAlreadyExistsException;
import com.borajunto.projetobora.service.PassageiroService;

@RestController
@RequestMapping("/api/passageiro")
@CrossOrigin(origins = "http://localhost:")
public class PassageiroController {
    
    @Autowired
    PassageiroService passageiroService;


    @PostMapping
    public ResponseEntity<?> inserirPassageiro(@Validated @RequestBody Passageiro passageiroEntity) {
        try {
            passageiroService.inserirPassageiro(passageiroEntity);
            return new ResponseEntity<>(passageiroEntity, HttpStatus.CREATED);
        } catch (EmailAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity<List<Passageiro>> listaPassageiros(){
        List<Passageiro> passageiros = passageiroService.listaPassageiros();
        return new ResponseEntity<>(passageiros, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPassageiro(@PathVariable int id){
        passageiroService.excluirPassageiro(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Passageiro passageiro) {
        boolean autenticado = passageiroService.login(passageiro.getEmail(), passageiro.getSenha());

        if(autenticado){
            return ResponseEntity.ok("Login autorizado!");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha inválidos");
    }
    




    

    
}
