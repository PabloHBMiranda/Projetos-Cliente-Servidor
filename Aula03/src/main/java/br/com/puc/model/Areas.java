package br.com.puc.model;

import java.io.Serializable;

public enum Areas implements Serializable {
    EX("Exatas"),
    HM("Humanas"),
    BL("Biológicas"),
    ART("Artes"),
    OUTROS("Outros");

    private String nomeArea;
    private Areas(String nomeArea){
        this.nomeArea = nomeArea;
    }

    public String nomeArea(){
        return this.nomeArea;
    }
}
