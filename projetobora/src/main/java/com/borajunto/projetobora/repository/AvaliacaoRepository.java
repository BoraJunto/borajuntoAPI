package com.borajunto.projetobora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.borajunto.projetobora.entidade.Avaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    
    List<Avaliacao> findByCaronaId(Long caronaId);
    
    List<Avaliacao> findByAvaliadorCpf(String cpfAvaliador);
    
    List<Avaliacao> findByAvaliadorCpf(String cpfAvaliado);
    
    @Query("SELECT AVG(a.nota) FROM Avaliacao a WHERE a.avaliado.cpf = :cpf")
    Double buscarMediaAvaliacoes(@Param("cpf") String cpf);
    
    @Query("SELECT a FROM Avaliacao a WHERE a.carona.id = :caronaId AND a.avaliador.cpf = :cpfAvaliador")
    Avaliacao buscarAvaliacaoPorCaronaEAvaliador(@Param("caronaId") Long caronaId, 
                                                  @Param("cpfAvaliador") String cpfAvaliador);
}
