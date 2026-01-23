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

import com.borajunto.projetobora.dto.AvaliacaoDTO;
import com.borajunto.projetobora.service.AvaliacaoService;


@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping
    public ResponseEntity<List<AvaliacaoDTO>> listarTodas() {
        return ResponseEntity.ok(avaliacaoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterAvaliacao(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(avaliacaoService.obterAvaliacao(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Avaliação não encontrada");
        }
    }

    @GetMapping("/carona/{caronaId}")
    public ResponseEntity<?> listarPorCarona(@PathVariable Long caronaId) {
        try {
            return ResponseEntity.ok(avaliacaoService.listarPorCarona(caronaId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na busca: " + e.getMessage());
        }
    }

    @GetMapping("/usuario/{cpf}")
    public ResponseEntity<?> listarAvaliacoesPorUsuario(@PathVariable String cpf) {
        try {
            return ResponseEntity.ok(avaliacaoService.listarAvaliacoesPorUsuario(cpf));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na busca: " + e.getMessage());
        }
    }

    @GetMapping("/media/{cpf}")
    public ResponseEntity<?> obterMediaAvaliacoes(@PathVariable String cpf) {
        try {
            Double media = avaliacaoService.obterMediaAvaliacoes(cpf);
            return ResponseEntity.ok(media != null ? media : 0.0);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao obter média: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> criarAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDTO) {
        try {
            AvaliacaoDTO nova = avaliacaoService.criarAvaliacao(avaliacaoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nova);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar avaliação: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarAvaliacao(@PathVariable Long id, @RequestBody AvaliacaoDTO avaliacaoDTO) {
        try {
            AvaliacaoDTO atualizada = avaliacaoService.atualizarAvaliacao(id, avaliacaoDTO);
            return ResponseEntity.ok(atualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar avaliação: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarAvaliacao(@PathVariable Long id) {
        try {
            avaliacaoService.deletarAvaliacao(id);
            return ResponseEntity.ok("Avaliação deletada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Avaliação não encontrada");
        }
    }
}
