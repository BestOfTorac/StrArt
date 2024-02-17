package com.strart.model.domain;

public class OpenStreetMapFeatures {

    String type;
    OpenStreetMapProperties properties;
    String[] bbox;
    OpenStreetMapGeometry geometry;

    public OpenStreetMapFeatures(String type, OpenStreetMapProperties properties, String[] bbox, OpenStreetMapGeometry geometry){
        this.type=type;
        this.properties=properties;
        this.bbox=bbox;
        this.geometry=geometry;
    }

    public OpenStreetMapGeometry getOpenStreetMapGeometry(){
        return geometry;
    }

}
