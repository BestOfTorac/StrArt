package com.strart.model.bean;
import java.util.ArrayList;
import java.util.List;

public class BeanEventi {

    private List<BeanEvento> listEvento;

    private String longitudine;

    private String latitudine;

    private String type;




    public BeanEventi(String longitudine, String latitudine, String type) {
        this.listEvento = new ArrayList<>();
        this.longitudine=longitudine;
        this.latitudine=latitudine;
        this.type=type;

    }

    public BeanEventi() {
        this.listEvento = new ArrayList<>();
    }

    public List<BeanEvento> getListEvento() {
            return listEvento;
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

    public void addEvento(BeanEvento beanEvento){
        listEvento.add(beanEvento);
    }


}
