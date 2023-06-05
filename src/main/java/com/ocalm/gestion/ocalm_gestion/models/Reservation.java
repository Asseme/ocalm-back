package com.ocalm.gestion.ocalm_gestion.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = this.paid + paid;
    }

    public double getRest() {
        return rest;
    }

    public void setRest(double rest) {
        this.rest = rest;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reservation(Long id, String lastName, String firstName, String contact, LocalDate startDate,
            LocalDate endDate,
            int quantity, double price, double amount, String heure, double paid, double rest) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.contact = contact;
        this.startDate = startDate;
        this.endDate = endDate;
        this.quantity = quantity;
        this.price = price;
        this.amount = amount;
        this.heure = heure;
        this.paid = paid;
        this.rest = rest;
    }

    public Reservation() {
    }

    public Long getId() {
        return id;
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
}
