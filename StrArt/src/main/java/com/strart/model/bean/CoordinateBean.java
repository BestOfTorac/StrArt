package com.strart.model.bean;

public class CoordinateBean {

    String indirizzo;
    String longitudine;
    String latitudine;

    String type;


    public CoordinateBean(String indirizzo, String longitudine, String latitudine, String type) {

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
