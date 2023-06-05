package com.ocalm.gestion.ocalm_gestion.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ocalm.gestion.ocalm_gestion.AppConfig;
import com.ocalm.gestion.ocalm_gestion.dto.ReservationDto;
import com.ocalm.gestion.ocalm_gestion.models.Reservation;
import com.ocalm.gestion.ocalm_gestion.models.Versement;
import com.ocalm.gestion.ocalm_gestion.repository.ReservationRepository;
import com.ocalm.gestion.ocalm_gestion.repository.VersementRepository;
import com.ocalm.gestion.ocalm_gestion.services.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationRepository reservationRepository;
    private final VersementRepository versementRepository;
    private final ReservationService reservationService;
    private final AppConfig appConfig;

    @Autowired
    public ReservationController(ReservationRepository reservationRepository, VersementRepository versementRepository
        ,ReservationService reservationService, AppConfig appConfig) {
        this.reservationRepository = reservationRepository;
        this.versementRepository = versementRepository;
        this.reservationService = reservationService;
        this.appConfig = appConfig;
    }

    // Endpoint pour créer une nouvelle réservation
    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        this.reservationService.computeQuantity(reservation);
        this.reservationService.computeAmountReservation(reservation);
        return reservationRepository.save(reservation);
    }

    // Endpoint pour récupérer toutes les réservations
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // Endpoint pour récupérer une réservation par son ID
    @GetMapping("/{id}")
    public ReservationDto getReservation(@PathVariable Long id) {
        ReservationDto reservationDto = reservationService.getReservationById(id);
        return reservationDto;
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<ByteArrayResource> generatePdf(@PathVariable Long id) throws IOException {
        // Reservation reservation = reservationRepository.findById(id).orElse(null);

        // Create a new PDF document
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        // Perform text replacements in the template
        Map<String, String> variables = new HashMap<>();
        variables.put("{startDate}", "reservation.getStartDate().toString()");
        replaceText(document, variables);

        // Convert the PDF document to byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        document.save(baos);
        document.close();
        byte[] pdfData = baos.toByteArray();

        // Create a ByteArrayResource from the PDF data
        ByteArrayResource resource = new ByteArrayResource(pdfData);

        // Set the HTTP response headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=generated.pdf");

        // Return the response with the PDF resource and appropriate headers
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfData.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    // Endpoint pour mettre à jour une réservation
    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable Long id, @RequestBody Reservation updatedReservation) {
        return reservationRepository.findById(id)
                .map(reservation -> {
                    reservation.setLastName(updatedReservation.getLastName());
                    reservation.setFirstName(updatedReservation.getFirstName());
                    reservation.setContact(updatedReservation.getContact());
                    reservation.setStartDate(updatedReservation.getStartDate());
                    reservation.setEndDate(updatedReservation.getEndDate());
                    reservation.setQuantity(updatedReservation.getQuantity());
                    reservation.setPrice(updatedReservation.getPrice());
                    reservation.setAmount(updatedReservation.getAmount());
                    return reservationRepository.save(reservation);
                })
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + id));
    }

    // Endpoint pour supprimer une réservation par son ID
    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationRepository.deleteById(id);
    }

    private void replaceText(PDDocument document, Map<String, String> replacements) throws IOException {
        // Load the template PDF
        InputStream templateStream = getClass().getResourceAsStream("./facture.pdf");
        PDDocument templateDoc = PDDocument.load(templateStream);

        // Close the template
        templateDoc.close();
    }
}
