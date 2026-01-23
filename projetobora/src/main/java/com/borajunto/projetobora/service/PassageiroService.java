package com.borajunto.projetobora.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borajunto.projetobora.dto.PassageiroDTO;
import com.borajunto.projetobora.entidade.Carona;
import com.borajunto.projetobora.entidade.Passageiro;
import com.borajunto.projetobora.entidade.StatusPassageiro;
import com.borajunto.projetobora.entidade.Usuario;
import com.borajunto.projetobora.repository.CaronaRepository;
import com.borajunto.projetobora.repository.PassageiroRepository;
import com.borajunto.projetobora.repository.UsuarioRepository;

@Service
public class PassageiroService {

    @Autowired
    private PassageiroRepository passageiroRepository;

    @Autowired
    private CaronaRepository caronaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CaronaService caronaService;

    public List<PassageiroDTO> listarTodos() {
        List<Passageiro> passageiros = passageiroRepository.findAll();
        return passageiros.stream().map(PassageiroDTO::new).toList();
    }

    public List<PassageiroDTO> listarPorCarona(Long caronaId) {
        List<Passageiro> passageiros = passageiroRepository.findByCaronaId(caronaId);
        return passageiros.stream().map(PassageiroDTO::new).toList();
    }

    public List<PassageiroDTO> listarPorUsuario(String cpfUsuario) {
        List<Passageiro> passageiros = passageiroRepository.findByUsuarioCpf(cpfUsuario);
        return passageiros.stream().map(PassageiroDTO::new).toList();
    }

    public List<PassageiroDTO> listarPorStatus(StatusPassageiro status) {
        List<Passageiro> passageiros = passageiroRepository.findByStatus(status);
        return passageiros.stream().map(PassageiroDTO::new).toList();
    }

    public PassageiroDTO solicitarVaga(Long caronaId, String cpfUsuario) {
        Optional<Carona> caronaOpt = caronaRepository.findById(caronaId);
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(cpfUsuario);

        if (caronaOpt.isEmpty()) {
            throw new IllegalArgumentException("Carona não encontrada");
        }
        if (usuarioOpt.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        Carona carona = caronaOpt.get();
        Usuario usuario = usuarioOpt.get();

        if (carona.getVagasDisponiveis() <= 0) {
            throw new IllegalArgumentException("Sem vagas disponíveis nesta carona");
        }

        // Verifica se usuário já requisitou esta carona
        Passageiro passageiroExistente = passageiroRepository.buscarPorUsuarioECarona(cpfUsuario, caronaId);
        if (passageiroExistente != null) {
            throw new IllegalArgumentException("Você já requisitou esta carona");
        }

        Passageiro passageiro = new Passageiro(carona, usuario);
        Passageiro passageiroSalvo = passageiroRepository.save(passageiro);

        return new PassageiroDTO(passageiroSalvo);
    }

    public PassageiroDTO aprovarPassageiro(Long passageiroId) {
        Optional<Passageiro> passageiroOpt = passageiroRepository.findById(passageiroId);

        if (passageiroOpt.isEmpty()) {
            throw new IllegalArgumentException("Passageiro não encontrado");
        }

        Passageiro passageiro = passageiroOpt.get();
        passageiro.setStatus(StatusPassageiro.APROVADO);
        passageiro.setDataAprovacao(LocalDateTime.now());

        // Reduz vagas disponíveis
        caronaService.reduzirVagasDisponiveis(passageiro.getCarona().getId());

        Passageiro passageiroAtualizado = passageiroRepository.save(passageiro);
        return new PassageiroDTO(passageiroAtualizado);
    }

    public PassageiroDTO rejeitarPassageiro(Long passageiroId) {
        Optional<Passageiro> passageiroOpt = passageiroRepository.findById(passageiroId);

        if (passageiroOpt.isEmpty()) {
            throw new IllegalArgumentException("Passageiro não encontrado");
        }

        Passageiro passageiro = passageiroOpt.get();
        passageiro.setStatus(StatusPassageiro.REJEITADO);

        Passageiro passageiroAtualizado = passageiroRepository.save(passageiro);
        return new PassageiroDTO(passageiroAtualizado);
    }

    public PassageiroDTO cancelarPassageiro(Long passageiroId) {
        Optional<Passageiro> passageiroOpt = passageiroRepository.findById(passageiroId);

        if (passageiroOpt.isEmpty()) {
            throw new IllegalArgumentException("Passageiro não encontrado");
        }

        Passageiro passageiro = passageiroOpt.get();
        if (passageiro.getStatus() == StatusPassageiro.APROVADO) {
            caronaService.aumentarVagasDisponiveis(passageiro.getCarona().getId());
        }
        passageiro.setStatus(StatusPassageiro.CANCELADO);

        Passageiro passageiroAtualizado = passageiroRepository.save(passageiro);
        return new PassageiroDTO(passageiroAtualizado);
    }

    public void deletarPassageiro(Long passageiroId) {
        passageiroRepository.deleteById(passageiroId);
    }

    public PassageiroDTO obterPassageiro(Long passageiroId) {
        Optional<Passageiro> passageiro = passageiroRepository.findById(passageiroId);
        return passageiro.map(PassageiroDTO::new).orElseThrow(() -> new IllegalArgumentException("Passageiro não encontrado"));
    }
}
