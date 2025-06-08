package com.example.desafioluizalabspessoadesenvolvedorabackend.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class Usuario {

    @Id
    private Integer userId;
    private String name;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Compra> orders;

    public Usuario() {
    }

    public Usuario(Integer userId, String name, List<Compra> orders) {
        this.userId = userId;
        this.name = name;
        this.orders = orders;
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

    public List<Compra> getOrders() {
        return orders;
    }

    public void setOrders(List<Compra> orders) {
        this.orders = orders;
    }
}
