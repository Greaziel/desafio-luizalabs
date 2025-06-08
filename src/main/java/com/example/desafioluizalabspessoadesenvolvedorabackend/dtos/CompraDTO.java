package com.example.desafioluizalabspessoadesenvolvedorabackend.dtos;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CompraDTO {

    private Integer order_id;
    private Double total;
    private Date data;
    private List<ProdutoDTO> produtos;
}
