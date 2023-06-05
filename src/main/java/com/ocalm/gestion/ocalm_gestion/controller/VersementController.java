package com.ocalm.gestion.ocalm_gestion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ocalm.gestion.ocalm_gestion.models.Reservation;
import com.ocalm.gestion.ocalm_gestion.models.Versement;
import com.ocalm.gestion.ocalm_gestion.models.payloads.VersementPayload;
import com.ocalm.gestion.ocalm_gestion.repository.ReservationRepository;
import com.ocalm.gestion.ocalm_gestion.repository.VersementRepository;
import com.ocalm.gestion.ocalm_gestion.services.ReservationService;

@RestController
@RequestMapping("/versements")
public class VersementController {
    private final VersementRepository versementRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;

    @Autowired
    public VersementController(VersementRepository versementRepository, ReservationRepository reservationRepository,
            ReservationService reservationService) {
        this.versementRepository = versementRepository;
        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
    }

    // Endpoint pour créer une nouvelle réservation
    @PostMapping
    public Versement createVersement(@RequestBody VersementPayload payload) {
        Reservation reservation = reservationRepository.findById(payload.getReservationId())
                .orElseThrow(
                        () -> new RuntimeException("Reservation not found with id: " + payload.getReservationId()));
        ;
        Versement versement = payload.getVersement();
        reservation.setPaid(versement.getAmount());
        reservationService.computeRest(reservation);
        versement.setReservation(reservation);
        List<Versement> versements = versementRepository.findByReservation(reservation);
        if(versements.size() >= 0) {
            versement.setOrdre(versements.size() + 1);
        }else{
            versement.setOrdre(1);
        }
        return versementRepository.save(versement);
    }

    // Endpoint pour récupérer toutes les réservations
    @GetMapping
    public List<Versement> getAllVersements() {
        return versementRepository.findAll();
    }

    // Endpoint pour récupérer une réservation par son ID
    @GetMapping("/{id}")
    public Versement getVersement(@PathVariable Long id) {
        return versementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Versements not found with id: " + id));
    }

    @GetMapping("reservation/{id}")
    public List<Versement> getVersementsByReservation(@PathVariable Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + id));
        ;
        return versementRepository.findByReservation(reservation);
    }

    @GetMapping("/last/{reservationId}")
    public Versement getLastVersementsByReservation(@PathVariable Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + reservationId));
        ;
        int size = versementRepository.findByReservation(reservation).size() > 0
                ? versementRepository.findByReservation(reservation).size()
                : 1;
        return versementRepository.findByReservation(reservation) != null
                ? versementRepository.findByReservation(reservation).get(size - 1)
                : null;
    }

    // Endpoint pour mettre à jour une réservation
    @PutMapping("/{id}")
    public Versement updateVersement(@PathVariable Long id, @RequestBody Versement updatedVersement) {
        return versementRepository.findById(id)
                .map(versement -> {
                    versement.setName(updatedVersement.getName());
                    versement.setAmount(updatedVersement.getAmount());
                    versement.setDateVersement(updatedVersement.getDateVersement());
                    return versementRepository.save(versement);
                })
                .orElseThrow(() -> new RuntimeException("Versements not found with id: " + id));
    }

    // Endpoint pour supprimer une réservation par son ID
    @DeleteMapping("/{id}")
    public void deleteVersement(@PathVariable Long id) {
        versementRepository.deleteById(id);
    }
}
