package com.borajunto.projetobora.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borajunto.projetobora.dto.CaronaDTO;
import com.borajunto.projetobora.entidade.Carona;
import com.borajunto.projetobora.entidade.StatusCarona;
import com.borajunto.projetobora.entidade.Usuario;
import com.borajunto.projetobora.repository.CaronaRepository;
import com.borajunto.projetobora.repository.UsuarioRepository;

@Service
public class CaronaService {

    @Autowired
    private CaronaRepository caronaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<CaronaDTO> listarTodas() {
        List<Carona> caronas = caronaRepository.findAll();
        return caronas.stream().map(CaronaDTO::new).toList();
    }

    public List<CaronaDTO> listarAtivas() {
        List<Carona> caronas = caronaRepository.findByStatus(StatusCarona.ATIVA);
        return caronas.stream().map(CaronaDTO::new).toList();
    }

    public List<CaronaDTO> buscarPorOrigemDestino(String origem, String destino) {
        List<Carona> caronas = caronaRepository.buscarPorOrigemDestinoStatus(origem, destino, StatusCarona.ATIVA);
        return caronas.stream().map(CaronaDTO::new).toList();
    }

    public List<CaronaDTO> buscarPorMotorista(String cpfMotorista) {
        List<Carona> caronas = caronaRepository.findByMotoristaCpf(cpfMotorista);
        return caronas.stream().map(CaronaDTO::new).toList();
    }

    public List<CaronaDTO> buscarPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        List<Carona> caronas = caronaRepository.buscarPorPeriodo(dataInicio, dataFim);
        return caronas.stream().map(CaronaDTO::new).toList();
    }

    public CaronaDTO criarCarona(CaronaDTO caronaDTO) {
        Optional<Usuario> motorista = usuarioRepository.findById(caronaDTO.getCpfMotorista());
        
        if (motorista.isEmpty()) {
            throw new IllegalArgumentException("Motorista não encontrado");
        }

        Carona carona = new Carona(
            motorista.get(),
            caronaDTO.getOrigem(),
            caronaDTO.getDestino(),
            caronaDTO.getDataHoraSaida(),
            caronaDTO.getVagas(),
            caronaDTO.getValor()
        );
        carona.setDescricao(caronaDTO.getDescricao());

        Carona caronaSalva = caronaRepository.save(carona);
        return new CaronaDTO(caronaSalva);
    }

    public CaronaDTO atualizarCarona(Long id, CaronaDTO caronaDTO) {
        Optional<Carona> caronaOpt = caronaRepository.findById(id);
        
        if (caronaOpt.isEmpty()) {
            throw new IllegalArgumentException("Carona não encontrada");
        }

        Carona carona = caronaOpt.get();
        carona.setOrigem(caronaDTO.getOrigem());
        carona.setDestino(caronaDTO.getDestino());
        carona.setDataHoraSaida(caronaDTO.getDataHoraSaida());
        carona.setVagas(caronaDTO.getVagas());
        carona.setValor(caronaDTO.getValor());
        carona.setDescricao(caronaDTO.getDescricao());
        carona.setDataAtualizacao(LocalDateTime.now());

        Carona caronaAtualizada = caronaRepository.save(carona);
        return new CaronaDTO(caronaAtualizada);
    }

    public void cancelarCarona(Long id) {
        Optional<Carona> caronaOpt = caronaRepository.findById(id);
        
        if (caronaOpt.isEmpty()) {
            throw new IllegalArgumentException("Carona não encontrada");
        }

        Carona carona = caronaOpt.get();
        carona.setStatus(StatusCarona.CANCELADA);
        caronaRepository.save(carona);
    }

    public void deletarCarona(Long id) {
        caronaRepository.deleteById(id);
    }

    public CaronaDTO obterCarona(Long id) {
        Optional<Carona> carona = caronaRepository.findById(id);
        return carona.map(CaronaDTO::new).orElseThrow(() -> new IllegalArgumentException("Carona não encontrada"));
    }

    public void reduzirVagasDisponiveis(Long caronaId) {
        Optional<Carona> caronaOpt = caronaRepository.findById(caronaId);
        if (caronaOpt.isPresent()) {
            Carona carona = caronaOpt.get();
            if (carona.getVagasDisponiveis() > 0) {
                carona.setVagasDisponiveis(carona.getVagasDisponiveis() - 1);
                if (carona.getVagasDisponiveis() == 0) {
                    carona.setStatus(StatusCarona.COMPLETA);
                }
                caronaRepository.save(carona);
            }
        }
    }

    public void aumentarVagasDisponiveis(Long caronaId) {
        Optional<Carona> caronaOpt = caronaRepository.findById(caronaId);
        if (caronaOpt.isPresent()) {
            Carona carona = caronaOpt.get();
            if (carona.getVagasDisponiveis() < carona.getVagas()) {
                carona.setVagasDisponiveis(carona.getVagasDisponiveis() + 1);
                if (carona.getStatus() == StatusCarona.COMPLETA) {
                    carona.setStatus(StatusCarona.ATIVA);
                }
                caronaRepository.save(carona);
            }
        }
    }
}
