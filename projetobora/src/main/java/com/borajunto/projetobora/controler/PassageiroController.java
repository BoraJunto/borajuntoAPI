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

import com.borajunto.projetobora.dto.PassageiroDTO;
import com.borajunto.projetobora.entidade.StatusPassageiro;
import com.borajunto.projetobora.service.PassageiroService;


@RestController
@RequestMapping("/api/passageiros")
public class PassageiroController {

    @Autowired
    private PassageiroService passageiroService;

    @GetMapping
    public ResponseEntity<List<PassageiroDTO>> listarTodos() {
        return ResponseEntity.ok(passageiroService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterPassageiro(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(passageiroService.obterPassageiro(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Passageiro não encontrado");
        }
    }

    @GetMapping("/carona/{caronaId}")
    public ResponseEntity<?> listarPorCarona(@PathVariable Long caronaId) {
        try {
            return ResponseEntity.ok(passageiroService.listarPorCarona(caronaId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na busca: " + e.getMessage());
        }
    }

    @GetMapping("/usuario/{cpf}")
    public ResponseEntity<?> listarPorUsuario(@PathVariable String cpf) {
        try {
            return ResponseEntity.ok(passageiroService.listarPorUsuario(cpf));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na busca: " + e.getMessage());
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> listarPorStatus(@PathVariable String status) {
        try {
            StatusPassageiro st = StatusPassageiro.valueOf(status.toUpperCase());
            return ResponseEntity.ok(passageiroService.listarPorStatus(st));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Status inválido");
        }
    }

    @PostMapping("/solicitar/{caronaId}/{cpfUsuario}")
    public ResponseEntity<?> solicitarVaga(@PathVariable Long caronaId, @PathVariable String cpfUsuario) {
        try {
            PassageiroDTO passageiro = passageiroService.solicitarVaga(caronaId, cpfUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(passageiro);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao solicitar vaga: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/aprovar")
    public ResponseEntity<?> aprovarPassageiro(@PathVariable Long id) {
        try {
            PassageiroDTO aprovado = passageiroService.aprovarPassageiro(id);
            return ResponseEntity.ok(aprovado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao aprovar passageiro: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/rejeitar")
    public ResponseEntity<?> rejeitarPassageiro(@PathVariable Long id) {
        try {
            PassageiroDTO rejeitado = passageiroService.rejeitarPassageiro(id);
            return ResponseEntity.ok(rejeitado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao rejeitar passageiro: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelarPassageiro(@PathVariable Long id) {
        try {
            PassageiroDTO cancelado = passageiroService.cancelarPassageiro(id);
            return ResponseEntity.ok(cancelado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cancelar passageiro: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPassageiro(@PathVariable Long id) {
        try {
            passageiroService.deletarPassageiro(id);
            return ResponseEntity.ok("Passageiro deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Passageiro não encontrado");
        }
    }
}
