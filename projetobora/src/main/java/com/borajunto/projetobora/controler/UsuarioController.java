package com.borajunto.projetobora.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping(value = "/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarTodos(){
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable("cpf") String cpf){
        try {
            return ResponseEntity.ok(usuarioService.buscarPorId(cpf));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> inserir(@RequestBody UsuarioDTO usuarioDTO){
        try {
            usuarioService.inserir(usuarioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao registrar usuário: " + e.getMessage());
        }
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<?> alterar(@PathVariable("cpf") String cpf, @RequestBody UsuarioDTO usuarioDTO){
        try {
            usuarioDTO.setCpf(cpf);
            UsuarioDTO atualizado = usuarioService.alterar(usuarioDTO);
            return ResponseEntity.ok(atualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<?> excluir(@PathVariable("cpf") String cpf){
        try {
            usuarioService.excluir(cpf);
            return ResponseEntity.ok("Usuário deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
    }
}
