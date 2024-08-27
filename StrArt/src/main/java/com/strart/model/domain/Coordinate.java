package com.strart.model.domain;

public class Coordinate {

    private String indirizzo;
    private String longitudine;
    private String latitudine;

    private String type;


    public Coordinate(String indirizzo, String longitudine, String latitudine, String type) {

        this.indirizzo = indirizzo;
        this.longitudine = longitudine;
        this.latitudine = latitudine;
        this.type = type;
    }

    public String getIndirizzo() {
            return indirizzo;
        }

    public String getLongitudine() {
        return longitudine;
    }

    public String getLatitudine() {
        return latitudine;
    }

    public String getType() {
        return type;
    }

}
