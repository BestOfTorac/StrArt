package com.strart.model.domain;


public class ResponseOpenStreetMap {
    String type;
    String licence;
    OpenStreetMapFeatures[] features;


    public ResponseOpenStreetMap(String type, String licence, OpenStreetMapFeatures[] features){
        this.type=type;
        this.licence=licence;
        this.features=features;
    }

    public OpenStreetMapFeatures[] getOpenStreetMapFeatures(){
        return features;
    }



}
