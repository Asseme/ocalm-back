package com.ocalm.gestion.ocalm_gestion.services.servicesImpl;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ocalm.gestion.ocalm_gestion.dto.ReservationDto;
import com.ocalm.gestion.ocalm_gestion.models.Reservation;
import com.ocalm.gestion.ocalm_gestion.models.Versement;
import com.ocalm.gestion.ocalm_gestion.repository.ReservationRepository;
import com.ocalm.gestion.ocalm_gestion.repository.VersementRepository;
import com.ocalm.gestion.ocalm_gestion.services.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ModelMapper modelMapper;
    private final ReservationRepository reservationRepository;
    private final VersementRepository versementRepository;

    public ReservationServiceImpl(ModelMapper modelMapper, ReservationRepository reservationRepository, VersementRepository versementRepository) {
        this.modelMapper = modelMapper;
        this.reservationRepository = reservationRepository;
        this.versementRepository = versementRepository;
    }

    @Override
    public void computeAmountReservation(Reservation reservation) {
        reservation.setAmount(reservation.getPrice() * reservation.getQuantity());
    }

    @Override
    public void computeQuantity(Reservation reservation) {
        int nbDays = (int) ChronoUnit.DAYS.between(reservation.getStartDate(), reservation.getEndDate());
        reservation.setQuantity(nbDays);
    }

    @Override
    public void computeRest(Reservation reservation) {
        double amount = reservation.getAmount();
        double paid = reservation.getPaid();
        double rest = amount - paid;
        reservation.setRest(rest);
    }

    @Override
    public ReservationDto getReservationById(Long id) {
        Reservation reservation = this.reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + id));
        ReservationDto reservationDto = this.modelMapper.map(reservation, ReservationDto.class);
        List<Versement> versements = this.versementRepository.findByReservation(reservation);
        reservationDto.setVersements(versements);
        return reservationDto;
    }

}
