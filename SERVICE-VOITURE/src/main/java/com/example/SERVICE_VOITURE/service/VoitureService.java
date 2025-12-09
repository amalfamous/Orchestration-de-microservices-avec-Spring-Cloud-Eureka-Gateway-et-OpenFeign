package com.example.SERVICE_VOITURE.service;


import com.example.SERVICE_VOITURE.entities.Client;
import com.example.SERVICE_VOITURE.entities.Voiture;
import com.example.SERVICE_VOITURE.entities.CarResponse;
import com.example.SERVICE_VOITURE.repositories.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoitureService {

    @Autowired
    private VoitureRepository VoitureRepository;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * URL du service client via la Gateway
     * Remarque: Dans un environnement de production, cette URL serait configurée
     * dans le fichier de propriétés ou récupérée via un service de découverte
     */
    private static final String CLIENT_SERVICE_URL =
            "http://localhost:8888/SERVICE-CLIENT/api/client/";

    /**
     * Récupère toutes les voitures avec les détails des clients
     */
    public List<CarResponse> findAll() {
        List<Voiture> Voitures = VoitureRepository.findAll();

        return Voitures.stream()
                .map(this::mapToCarResponse)
                .collect(Collectors.toList());
    }

    /**
     * Récupère une voiture par son ID avec les détails du client
     */
    public CarResponse findById(Long id) throws Exception {
        Voiture Voiture = VoitureRepository.findById(id)
                .orElseThrow(() -> new Exception("Voiture non trouvée avec l'ID: " + id));

        return mapToCarResponse(Voiture);
    }

    /**
     * Convertit une entité Voiture en CarResponse en récupérant les détails du client
     */
    private CarResponse mapToCarResponse(Voiture Voiture) {
        // Récupération du client depuis le service client
        Client client = null;
        try {
            client = restTemplate.getForObject(
                    CLIENT_SERVICE_URL + Voiture.getId_client(),
                    Client.class);
        } catch (Exception e) {
            // En cas d'erreur, on continue avec un client null
            System.err.println("Erreur lors de la récupération du client: " + e.getMessage());
        }

        // Construction de la réponse
        return CarResponse.builder()
                .id(Voiture.getId())
                .brand(Voiture.getMarque())
                .model(Voiture.getModel())
                .matricule(Voiture.getMatricule())
                .client(client)
                .build();
    }
}