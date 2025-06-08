package com.example.desafioluizalabspessoadesenvolvedorabackend.mappers;

import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.ProdutoDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.models.Produto;

public class ProdutoMapper {

    public static Produto produtoDtoToProduto(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setProduct_id(produtoDTO.getProduto_id());
        produto.setValue(produtoDTO.getValue());
        return produto;
    }

    public static ProdutoDTO produtoToProdutoDTO(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setProduto_id(produto.getProduct_id());
        dto.setValue(produto.getValue());
        return dto;
    }
}
