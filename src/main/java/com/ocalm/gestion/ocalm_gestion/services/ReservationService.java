package com.ocalm.gestion.ocalm_gestion.services;

import com.ocalm.gestion.ocalm_gestion.dto.ReservationDto;
import com.ocalm.gestion.ocalm_gestion.models.Reservation;

public interface ReservationService {
   public void computeAmountReservation(Reservation reservation);
   public void computeQuantity(Reservation reservation);
   public void computeRest(Reservation reservation);
   public ReservationDto getReservationById(Long id);
}
