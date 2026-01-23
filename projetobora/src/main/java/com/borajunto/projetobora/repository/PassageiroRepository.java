package com.borajunto.projetobora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.borajunto.projetobora.entidade.Passageiro;
import com.borajunto.projetobora.entidade.StatusPassageiro;

@Repository
public interface PassageiroRepository extends JpaRepository<Passageiro, Long> {
    
    List<Passageiro> findByCaronaId(Long caronaId);
    
    List<Passageiro> findByUsuarioCpf(String cpfUsuario);
    
    List<Passageiro> findByStatus(StatusPassageiro status);
    
    @Query("SELECT p FROM Passageiro p WHERE p.carona.id = :caronaId AND p.status = :status")
    List<Passageiro> buscarPorCaronaEStatus(@Param("caronaId") Long caronaId, 
                                             @Param("status") StatusPassageiro status);
    
    @Query("SELECT p FROM Passageiro p WHERE p.usuario.cpf = :cpf AND p.carona.id = :caronaId")
    Passageiro buscarPorUsuarioECarona(@Param("cpf") String cpf, @Param("caronaId") Long caronaId);
}
