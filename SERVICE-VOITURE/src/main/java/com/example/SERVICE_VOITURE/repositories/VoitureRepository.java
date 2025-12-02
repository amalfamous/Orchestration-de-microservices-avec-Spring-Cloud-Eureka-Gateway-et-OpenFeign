package com.example.SERVICE_VOITURE.repositories;

import com.example.SERVICE_VOITURE.entities.Voiture;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture, Long> {
    @Query("SELECT v FROM Voiture v WHERE v.id_client = :idClient")
    List<Voiture> findByIdClient(@Param("idClient") Long idClient);

}
