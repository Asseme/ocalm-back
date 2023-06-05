package com.ocalm.gestion.ocalm_gestion.models.payloads;

import com.ocalm.gestion.ocalm_gestion.models.Versement;

public class VersementPayload {
    Long reservationId;
    Versement versement;
    public Long getReservationId() {
        return reservationId;
    }
    public Versement getVersement() {
        return versement;
    }
}
