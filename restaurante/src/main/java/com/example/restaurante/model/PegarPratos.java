package com.example.restaurante.model;

public class PegarPratos {

    private int id;

    private float valor;

    private String descricao;

    private String nomeprato;

    private String categoria;

    public PegarPratos() {
    }

    public PegarPratos(int id, float valor, String descricao, String nomeprato, String categoria) {
        this.id = id;
        this.valor = valor;
        this.descricao = descricao;
        this.nomeprato = nomeprato;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeprato() {
        return nomeprato;
    }

    public void setNomeprato(String nomeprato) {
        this.nomeprato = nomeprato;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
