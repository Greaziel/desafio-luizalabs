package com.example.desafioluizalabspessoadesenvolvedorabackend.models;

import java.util.Date;
import java.util.List;

public class Compra {

    private Integer order_id;
    private Double total;
    private Date data;
    private List<Produto> produtos;

    public Compra() {
    }

    public Compra(Integer order_id, Double total, Date data, List<Produto> produtos) {
        this.order_id = order_id;
        this.total = total;
        this.data = data;
        this.produtos = produtos;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
