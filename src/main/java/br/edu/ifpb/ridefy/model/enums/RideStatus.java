package br.edu.ifpb.ridefy.model.enums;

public enum RideStatus {

    REQUESTED("Solicitação realizada"),
    ACCEPTED("Aceito"),
    CANCELED("Cancelada"),
    FINISHED("Finalizada");

    public final String description;

    RideStatus(String description) {
        this.description = description;
    }

}
