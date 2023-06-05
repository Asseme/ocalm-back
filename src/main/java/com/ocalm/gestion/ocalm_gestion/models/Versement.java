package com.ocalm.gestion.ocalm_gestion.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "versement")
public class Versement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    LocalDate dateVersement;
    String name;
    Double amount;
    Integer ordre;
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    Reservation reservation;
    
    public Versement(LocalDate dateVersement, String name, Double amount, Integer ordre, Reservation reservation) {
        this.dateVersement = dateVersement;
        this.name = name;
        this.amount = amount;
        this.ordre = ordre;
        this.reservation = reservation;
    }

    public Versement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateVersement() {
        return dateVersement;
    }

    public void setDateVersement(LocalDate dateVersement) {
        this.dateVersement = dateVersement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getOrdre() {
        return ordre;
    }

    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    

    

    
    

}
