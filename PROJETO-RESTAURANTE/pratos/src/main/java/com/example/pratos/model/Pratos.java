package com.example.pratos.model;

public class Pratos {

    private Long id;

    private String nomeprato;

    private float valor;

    private String categoria;

    private String descricao;

    public Pratos(){}

    public Pratos(Long id, String nomeprato, float valor, String categoria, String descricao) {
        this.id = id;
        this.nomeprato = nomeprato;
        this.valor = valor;
        this.categoria = categoria;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeprato() {
        return nomeprato;
    }

    public void setNomeprato(String nomeprato) {
        this.nomeprato = nomeprato;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
