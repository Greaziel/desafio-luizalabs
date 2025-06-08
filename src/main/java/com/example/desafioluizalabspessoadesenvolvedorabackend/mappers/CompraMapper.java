package com.example.desafioluizalabspessoadesenvolvedorabackend.mappers;

import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.CompraDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.ProdutoDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.models.Compra;
import com.example.desafioluizalabspessoadesenvolvedorabackend.models.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompraMapper {

    private final ProdutoMapper produtoMapper = new ProdutoMapper();

    public static Compra compraDTOToCompra(CompraDTO compraDTO) {

        Compra compra = new Compra();
        compra.setOrder_id(compraDTO.getOrder_id());
        compra.setTotal(compraDTO.getTotal());
        compra.setDate(compraDTO.getDate());

        if (compraDTO.getProducts() != null) {
            List<Produto> produtos = compraDTO.getProducts().stream()
                    .map(ProdutoMapper::produtoDtoToProduto)
                    .collect(Collectors.toList());

            compra.setProducts(produtos);

            produtos.forEach(prod -> prod.setCompra(compra));
        } else {
            compra.setProducts(new ArrayList<>());
        }

        return compra;
    }

    public static CompraDTO compraToCompraDTO(Compra compra) {

        CompraDTO dto = new CompraDTO();
        dto.setOrder_id(compra.getOrder_id());
        dto.setTotal(compra.getTotal());
        dto.setDate(compra.getDate());

        if (compra.getProducts() != null) {
            List<ProdutoDTO> produtosDTO = compra.getProducts().stream()
                    .map(ProdutoMapper::produtoToProdutoDTO)
                    .collect(Collectors.toList());

            dto.setProducts(produtosDTO);
        } else {
            dto.setProducts(new ArrayList<>());
        }

        return dto;

    }
}
