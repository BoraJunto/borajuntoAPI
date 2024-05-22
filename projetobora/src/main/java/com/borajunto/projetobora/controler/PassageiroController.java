package com.borajunto.projetobora.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.borajunto.projetobora.entidade.Passageiro;
import com.borajunto.projetobora.service.PassageiroService;

@RestController
@RequestMapping("/api/passageiro")
public class PassageiroController {
    
    @Autowired
    PassageiroService passageiroService;

    @PostMapping
    public ResponseEntity<Passageiro> inserirPassageiro(@RequestBody Passageiro passageiroEntity) {        
        passageiroService.inserirPassageiro(passageiroEntity);
        return new ResponseEntity<>(passageiroEntity, HttpStatus.CREATED);
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




    

    
}
