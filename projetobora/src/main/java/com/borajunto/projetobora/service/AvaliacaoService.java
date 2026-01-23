package com.borajunto.projetobora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borajunto.projetobora.dto.AvaliacaoDTO;
import com.borajunto.projetobora.entidade.Avaliacao;
import com.borajunto.projetobora.entidade.Carona;
import com.borajunto.projetobora.entidade.Usuario;
import com.borajunto.projetobora.repository.AvaliacaoRepository;
import com.borajunto.projetobora.repository.CaronaRepository;
import com.borajunto.projetobora.repository.UsuarioRepository;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private CaronaRepository caronaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<AvaliacaoDTO> listarTodas() {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();
        return avaliacoes.stream().map(AvaliacaoDTO::new).toList();
    }

    public List<AvaliacaoDTO> listarPorCarona(Long caronaId) {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByCaronaId(caronaId);
        return avaliacoes.stream().map(AvaliacaoDTO::new).toList();
    }

    public List<AvaliacaoDTO> listarAvaliacoesPorUsuario(String cpfUsuario) {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByAvaliadorCpf(cpfUsuario);
        return avaliacoes.stream().map(AvaliacaoDTO::new).toList();
    }

    public Double obterMediaAvaliacoes(String cpfUsuario) {
        return avaliacaoRepository.buscarMediaAvaliacoes(cpfUsuario);
    }

    public AvaliacaoDTO criarAvaliacao(AvaliacaoDTO avaliacaoDTO) {
        Optional<Carona> caronaOpt = caronaRepository.findById(avaliacaoDTO.getCaronaId());
        Optional<Usuario> avaliadorOpt = usuarioRepository.findById(avaliacaoDTO.getCpfAvaliador());
        Optional<Usuario> avaliadoOpt = usuarioRepository.findById(avaliacaoDTO.getCpfAvaliado());

        if (caronaOpt.isEmpty()) {
            throw new IllegalArgumentException("Carona não encontrada");
        }
        if (avaliadorOpt.isEmpty()) {
            throw new IllegalArgumentException("Avaliador não encontrado");
        }
        if (avaliadoOpt.isEmpty()) {
            throw new IllegalArgumentException("Avaliado não encontrado");
        }

        if (avaliacaoDTO.getNota() < 1 || avaliacaoDTO.getNota() > 5) {
            throw new IllegalArgumentException("Nota deve estar entre 1 e 5");
        }

        Avaliacao avaliacao = new Avaliacao(
            caronaOpt.get(),
            avaliadorOpt.get(),
            avaliadoOpt.get(),
            avaliacaoDTO.getNota(),
            avaliacaoDTO.getComentario()
        );

        Avaliacao avaliacaoSalva = avaliacaoRepository.save(avaliacao);
        return new AvaliacaoDTO(avaliacaoSalva);
    }

    public AvaliacaoDTO atualizarAvaliacao(Long id, AvaliacaoDTO avaliacaoDTO) {
        Optional<Avaliacao> avaliacaoOpt = avaliacaoRepository.findById(id);

        if (avaliacaoOpt.isEmpty()) {
            throw new IllegalArgumentException("Avaliação não encontrada");
        }

        if (avaliacaoDTO.getNota() < 1 || avaliacaoDTO.getNota() > 5) {
            throw new IllegalArgumentException("Nota deve estar entre 1 e 5");
        }

        Avaliacao avaliacao = avaliacaoOpt.get();
        avaliacao.setNota(avaliacaoDTO.getNota());
        avaliacao.setComentario(avaliacaoDTO.getComentario());

        Avaliacao avaliacaoAtualizada = avaliacaoRepository.save(avaliacao);
        return new AvaliacaoDTO(avaliacaoAtualizada);
    }

    public void deletarAvaliacao(Long id) {
        avaliacaoRepository.deleteById(id);
    }

    public AvaliacaoDTO obterAvaliacao(Long id) {
        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);
        return avaliacao.map(AvaliacaoDTO::new).orElseThrow(() -> new IllegalArgumentException("Avaliação não encontrada"));
    }
}
