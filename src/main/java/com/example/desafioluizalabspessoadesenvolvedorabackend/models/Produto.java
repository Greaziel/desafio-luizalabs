package com.example.desafioluizalabspessoadesenvolvedorabackend.models;

public class Produto {

    private Integer produto_id;
    private String value;

    public Produto() {
    }

    public Produto(Integer produto_id, String value) {
        this.produto_id = produto_id;
        this.value = value;
    }

    public Integer getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(Integer produto_id) {
        this.produto_id = produto_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
