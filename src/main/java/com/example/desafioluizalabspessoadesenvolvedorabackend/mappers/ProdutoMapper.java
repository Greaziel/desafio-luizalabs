package com.example.desafioluizalabspessoadesenvolvedorabackend.mappers;

import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.ProdutoDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.models.Produto;

public class ProdutoMapper {

    public Produto produtoDTOToProduto(ProdutoDTO produtoDTO){
        Produto produto = new Produto();
        produto.setProduto_id(produtoDTO.getProduto_id());
        produto.setValue(produtoDTO.getValue());

        return produto;
    }

    public ProdutoDTO produtoToProdutoDTO(Produto produto){
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setProduto_id(produto.getProduto_id());
        produtoDTO.setValue(produto.getValue());

        return produtoDTO;
    }
}
