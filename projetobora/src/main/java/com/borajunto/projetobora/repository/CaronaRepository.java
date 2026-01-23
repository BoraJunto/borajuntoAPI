package com.borajunto.projetobora.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.borajunto.projetobora.entidade.Carona;
import com.borajunto.projetobora.entidade.StatusCarona;

@Repository
public interface CaronaRepository extends JpaRepository<Carona, Long> {
    
    List<Carona> findByOrigem(String origem);
    
    List<Carona> findByDestino(String destino);
    
    List<Carona> findByStatus(StatusCarona status);
    
    List<Carona> findByMotoristaId(String cpfMotorista);
    
    @Query("SELECT c FROM Carona c WHERE c.origem = :origem AND c.destino = :destino AND c.status = :status")
    List<Carona> buscarPorOrigemDestinoStatus(@Param("origem") String origem, 
                                               @Param("destino") String destino, 
                                               @Param("status") StatusCarona status);
    
    @Query("SELECT c FROM Carona c WHERE c.dataHoraSaida BETWEEN :dataInicio AND :dataFim")
    List<Carona> buscarPorPeriodo(@Param("dataInicio") LocalDateTime dataInicio, 
                                    @Param("dataFim") LocalDateTime dataFim);
}
