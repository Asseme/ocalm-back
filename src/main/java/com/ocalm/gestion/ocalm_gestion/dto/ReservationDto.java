package com.ocalm.gestion.ocalm_gestion.dto;

import java.time.LocalDate;
import java.util.List;

import com.ocalm.gestion.ocalm_gestion.models.Versement;

public class ReservationDto {
    private Long id;
    private String lastName;
    private String firstName;
    private String contact;
    private LocalDate startDate;
    private LocalDate endDate;
    private int quantity;
    private double price;
    private double amount;
    private String heure;
    private double paid = 0;
    private double rest;
    private List<Versement> versements;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getHeure() {
        return heure;
    }
    public void setHeure(String heure) {
        this.heure = heure;
    }
    public double getPaid() {
        return paid;
    }
    public void setPaid(double paid) {
        this.paid = paid;
    }
    public double getRest() {
        return rest;
    }
    public void setRest(double rest) {
        this.rest = rest;
    }
    public List<Versement> getVersements() {
        return versements;
    }
    public void setVersements(List<Versement> versements) {
        this.versements = versements;
    }
}