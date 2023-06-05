package com.ocalm.gestion.ocalm_gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocalm.gestion.ocalm_gestion.models.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
