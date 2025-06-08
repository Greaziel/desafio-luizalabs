package com.example.desafioluizalabspessoadesenvolvedorabackend.mappers;

import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.CompraDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.ProdutoDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.models.Compra;
import com.example.desafioluizalabspessoadesenvolvedorabackend.models.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class CompraMapper {

    private final ProdutoMapper produtoMapper = new ProdutoMapper();

    public Compra compraDTOToCompra(CompraDTO compraDTO) {
        if (compraDTO == null) return null;

        Compra compra = new Compra();
        compra.setOrder_id(compraDTO.getOrder_id());
        compra.setTotal(compraDTO.getTotal());
        compra.setData(compraDTO.getData());

        if (compraDTO.getProdutos() != null) {
            List<Produto> produtos = compraDTO.getProdutos()
                    .stream()
                    .map(produtoMapper::produtoDTOToProduto)
                    .collect(Collectors.toList());
            compra.setProdutos(produtos);
        }

        return compra;
    }

    public CompraDTO compraToCompraDTO(Compra compra) {
        if (compra == null) return null;

        CompraDTO compraDTO = new CompraDTO();
        compraDTO.setOrder_id(compra.getOrder_id());
        compraDTO.setTotal(compra.getTotal());
        compraDTO.setData(compra.getData());

        if (compra.getProdutos() != null) {
            List<ProdutoDTO> produtosDTO = compra.getProdutos()
                    .stream()
                    .map(produtoMapper::produtoToProdutoDTO)
                    .collect(Collectors.toList());
            compraDTO.setProdutos(produtosDTO);
        }

        return compraDTO;
    }
}
