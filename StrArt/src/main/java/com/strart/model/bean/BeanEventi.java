package com.strart.model.bean;

import com.strart.model.domain.ListEvento;

public class BeanEventi {

    ListEvento listEvento;

    Coordinate cordinate;



    public BeanEventi(ListEvento listEvento, Coordinate cordinate) {
        this.listEvento = listEvento;
        this.cordinate = cordinate;

    }

    public ListEvento getListEvento() {
            return listEvento;
        }

    public Coordinate getCordinate() {
        return cordinate;
    }


}
