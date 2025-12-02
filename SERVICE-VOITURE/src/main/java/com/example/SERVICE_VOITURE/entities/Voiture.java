package com.example.SERVICE_VOITURE.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.SERVICE_VOITURE.entities.Client;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Voiture {

    @Id
    @GeneratedValue
    private Long id;
    private String marque;
    private String matricule;
    private String model;

    @Column(name = "id_client")
    private Long id_client;

//    @ManyToOne
//    @Transient
//    private Client client;

    // Client récupéré via REST - NE SERA PAS persisté en DB
    @Transient  // SANS @ManyToOne !
    private Client client;
}