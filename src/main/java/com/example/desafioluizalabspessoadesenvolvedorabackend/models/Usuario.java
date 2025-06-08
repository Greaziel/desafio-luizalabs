package com.example.desafioluizalabspessoadesenvolvedorabackend.models;

public class Usuario {

    private Integer userId;
    private String name;
    private Compra compra;

    public Usuario() {
    }

    public Usuario(Integer userId, String name, Compra compra) {
        this.userId = userId;
        this.name = name;
        this.compra = compra;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }
}
