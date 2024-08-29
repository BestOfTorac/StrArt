package com.strart.model.bean;

public class CoordinateBean {

    private String indirizzoB;
    private String longitudineB;
    private String latitudineB;

    private String typeB;


    public CoordinateBean(String indirizzo, String longitudine, String latitudine, String type) {

        this.indirizzoB = indirizzo;
        this.longitudineB = longitudine;
        this.latitudineB = latitudine;
        this.typeB = type;
    }

    public CoordinateBean(String longitudine, String latitudine) {
        this.longitudineB = longitudine;
        this.latitudineB = latitudine;
    }

    public String getIndirizzo() {
            return indirizzoB;
        }

    public String getLongitudine() {
        return longitudineB;
    }

    public String getLatitudine() {
        return latitudineB;
    }

    public String getType() {
        return typeB;
    }

}
