package com.strart.model.domain;

public class OpenStreetMapGeometry {

    String type;
    String[] coordinates;

    public OpenStreetMapGeometry( String type, String[] coordinates){
        this.type=type;
        this.coordinates=coordinates;
    }

    public String[] getcoordinates(){
        return coordinates;
    }
}
