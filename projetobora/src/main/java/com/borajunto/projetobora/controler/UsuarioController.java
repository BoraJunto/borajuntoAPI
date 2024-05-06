package com.borajunto.projetobora.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.borajunto.projetobora.dto.UsuarioDTO;
import com.borajunto.projetobora.service.UsuarioService;


@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioDTO> listarTodos(){
        return usuarioService.listarTodos();
    }

    @PostMapping
    public void inserir(@RequestBody UsuarioDTO usuarioDTO){
        usuarioService.inserir(usuarioDTO);
    }



    @PutMapping
    public UsuarioDTO alterar(@RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.alterar(usuarioDTO);
    }


    //http://endereco/usuario/cpf
    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> excluir(@PathVariable("cpf") String cpf){
        usuarioService.excluir(cpf);   
        return ResponseEntity.ok().build();
    }
    
}
