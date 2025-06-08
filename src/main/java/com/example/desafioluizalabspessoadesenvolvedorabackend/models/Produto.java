package com.example.desafioluizalabspessoadesenvolvedorabackend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Products")
public class Produto {
    @Id
    private Integer product_id;

    private Double value;

    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;

    public Produto() {
    }

    public Produto(Integer product_id, Double value, Compra compra) {
        this.product_id = product_id;
        this.value = value;
        this.compra = compra;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }
}
