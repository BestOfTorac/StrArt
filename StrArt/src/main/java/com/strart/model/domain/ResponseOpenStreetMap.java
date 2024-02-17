package com.strart.model.domain;

import com.strart.model.domain.OpenStreetMapFeatures;

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
