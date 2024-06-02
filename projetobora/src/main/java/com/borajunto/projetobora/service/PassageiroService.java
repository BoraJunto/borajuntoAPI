package com.borajunto.projetobora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.borajunto.projetobora.entidade.Passageiro;
import com.borajunto.projetobora.exception.EmailAlreadyExistsException;
import com.borajunto.projetobora.repository.PassageiroRepository;

@Service
public class PassageiroService {

    @Autowired
    private PassageiroRepository passageiroRepository;

    @Transactional
    public void inserirPassageiro(Passageiro passageiroEntity){
        String email = passageiroEntity.getEmail();
        Boolean emailExiste = verificarEmail(email);

        if(!emailExiste){
            passageiroRepository.save(passageiroEntity);
        } else {
            throw new EmailAlreadyExistsException("Esse email já existe em nosso banco de dados");
        }
        
    }

    public List<Passageiro> listaPassageiros(){
        return passageiroRepository.findAll();
    }

    public void excluirPassageiro(int id){
        passageiroRepository.deleteById(id);
    }

    public boolean verificarEmail(String email){
        return passageiroRepository.existsByEmail(email);
    }

    public boolean login( String email, String senha){
        Optional<Passageiro> optionalPassageiro = passageiroRepository.findByEmail(email);

        if(optionalPassageiro.isPresent()){
            Passageiro passageiro = optionalPassageiro.get();
            return passageiro.getSenha().equals(senha);

        }
        return false;
    }
}
