package com.borajunto.projetobora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borajunto.projetobora.entidade.Passageiro;
import com.borajunto.projetobora.repository.PassageiroRepository;

@Service
public class PassageiroService {

    @Autowired
    private PassageiroRepository passageiroRepository;

    public void inserirPassageiro(Passageiro passageiroEntity){
        if (passageiroEntity == null || 
            passageiroEntity.getEmail() == null ||
            passageiroEntity.getCpf() == null ||
            passageiroEntity.getRa() == null ||
            passageiroEntity.getUniversidade() == null ||
            passageiroEntity.getNome() == null ||
            passageiroEntity.getSenha() == null ||
            passageiroEntity.getNascimento() == null ||
            passageiroEntity.getSexo() == null) {
            throw new IllegalArgumentException("Todos os campos devem ser preenchidos.");
        }

        passageiroRepository.save(passageiroEntity);
        
    }

    public List<Passageiro> listaPassageiros(){
        return passageiroRepository.findAll();
    }

    public void excluirPassageiro(int id){
        passageiroRepository.deleteById(id);
    }
}
