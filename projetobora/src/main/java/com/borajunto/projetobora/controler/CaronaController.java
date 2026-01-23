package com.borajunto.projetobora.controler;

import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.borajunto.projetobora.dto.CaronaDTO;
import com.borajunto.projetobora.service.CaronaService;


@RestController
@RequestMapping("/api/caronas")
public class CaronaController {

    @Autowired
    private CaronaService caronaService;

    @GetMapping
    public ResponseEntity<List<CaronaDTO>> listarTodas() {
        return ResponseEntity.ok(caronaService.listarTodas());
    }

    @GetMapping("/ativas")
    public ResponseEntity<List<CaronaDTO>> listarAtivas() {
        return ResponseEntity.ok(caronaService.listarAtivas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterCarona(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(caronaService.obterCarona(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carona não encontrada");
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorOrigemDestino(
            @RequestParam String origem,
            @RequestParam String destino) {
        try {
            return ResponseEntity.ok(caronaService.buscarPorOrigemDestino(origem, destino));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na busca: " + e.getMessage());
        }
    }

    @GetMapping("/motorista/{cpf}")
    public ResponseEntity<?> buscarPorMotorista(@PathVariable String cpf) {
        try {
            return ResponseEntity.ok(caronaService.buscarPorMotorista(cpf));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na busca: " + e.getMessage());
        }
    }

    @GetMapping("/periodo")
    public ResponseEntity<?> buscarPorPeriodo(
            @RequestParam String dataInicio,
            @RequestParam String dataFim) {
        try {
            LocalDateTime inicio = LocalDateTime.parse(dataInicio);
            LocalDateTime fim = LocalDateTime.parse(dataFim);
            return ResponseEntity.ok(caronaService.buscarPorPeriodo(inicio, fim));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na busca: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> criarCarona(@RequestBody CaronaDTO caronaDTO) {
        try {
            CaronaDTO novaCarona = caronaService.criarCarona(caronaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaCarona);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar carona: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCarona(@PathVariable Long id, @RequestBody CaronaDTO caronaDTO) {
        try {
            CaronaDTO atualizada = caronaService.atualizarCarona(id, caronaDTO);
            return ResponseEntity.ok(atualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar carona: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelarCarona(@PathVariable Long id) {
        try {
            caronaService.cancelarCarona(id);
            return ResponseEntity.ok("Carona cancelada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cancelar carona: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCarona(@PathVariable Long id) {
        try {
            caronaService.deletarCarona(id);
            return ResponseEntity.ok("Carona deletada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carona não encontrada");
        }
    }
}
