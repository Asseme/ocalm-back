package com.ocalm.gestion.ocalm_gestion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocalm.gestion.ocalm_gestion.models.Reservation;
import com.ocalm.gestion.ocalm_gestion.models.Versement;

public interface VersementRepository extends JpaRepository<Versement, Long> {
    List<Versement> findByReservation(Reservation reservation);
}
