package com.strart.model.domain;

public class OpenStreetMapProperties {

    public OpenStreetMapGeocoding geocoding;

    public OpenStreetMapProperties(OpenStreetMapGeocoding geocoding){
        this.geocoding=geocoding;
    }

    public OpenStreetMapGeocoding getGeocoding(){
        return geocoding;
    }
}
