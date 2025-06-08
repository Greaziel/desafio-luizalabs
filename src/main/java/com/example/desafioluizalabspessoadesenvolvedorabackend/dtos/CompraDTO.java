package com.example.desafioluizalabspessoadesenvolvedorabackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraDTO {

    private Integer order_id;
    private Double total;
    private Date date;
    private List<ProdutoDTO> products;

}
