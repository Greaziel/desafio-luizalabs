package com.example.desafioluizalabspessoadesenvolvedorabackend.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Ords")
public class Compra {
    @Id
    private Integer order_id;

    private Double total;

    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produto> products;

    public Compra() {
    }

    public Compra(Integer order_id, Double total, Date date, Usuario usuario, List<Produto> products) {
        this.order_id = order_id;
        this.total = total;
        this.date = date;
        this.usuario = usuario;
        this.products = products;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Produto> getProducts() {
        return products;
    }

    public void setProducts(List<Produto> products) {
        this.products = products;
    }
}
