package com.example.desafioluizalabspessoadesenvolvedorabackend.mappers;

import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.CompraDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.ProdutoDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.models.Compra;
import com.example.desafioluizalabspessoadesenvolvedorabackend.models.Produto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CompraMapperTest {

    @Test
    void testCompraDTOToCompra_withProducts() {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setProduto_id(1);
        produtoDTO.setValue(10.0);

        Date date = new Date();

        CompraDTO compraDTO = new CompraDTO();
        compraDTO.setOrder_id(100);
        compraDTO.setTotal(10.0);
        compraDTO.setDate(date);
        compraDTO.setProducts(Collections.singletonList(produtoDTO));

        Compra compra = CompraMapper.compraDTOToCompra(compraDTO);

        assertNotNull(compra);
        assertEquals(100, compra.getOrder_id());
        assertEquals(10.0, compra.getTotal());
        assertEquals(date, compra.getDate());
        assertNotNull(compra.getProducts());
        assertEquals(1, compra.getProducts().size());

        Produto produto = compra.getProducts().get(0);
        assertEquals(1, produto.getProduct_id());
        assertEquals(10.0, produto.getValue());
        assertEquals(compra, produto.getCompra());
    }

    @Test
    void testCompraDTOToCompra_withNullProducts() {
        Date date = new Date();

        CompraDTO compraDTO = new CompraDTO();
        compraDTO.setOrder_id(101);
        compraDTO.setTotal(20.0);
        compraDTO.setDate(date);
        compraDTO.setProducts(null);

        Compra compra = CompraMapper.compraDTOToCompra(compraDTO);

        assertNotNull(compra);
        assertEquals(101, compra.getOrder_id());
        assertEquals(20.0, compra.getTotal());
        assertEquals(date, compra.getDate());
        assertNotNull(compra.getProducts());
        assertTrue(compra.getProducts().isEmpty());
    }

    @Test
    void testCompraToCompraDTO_withProducts() {
        Produto produto = new Produto();
        produto.setProduct_id(2);
        produto.setValue(50.0);

        Date date = new Date();

        Compra compra = new Compra();
        compra.setOrder_id(200);
        compra.setTotal(50.0);
        compra.setDate(date);
        compra.setProducts(Collections.singletonList(produto));

        CompraDTO compraDTO = CompraMapper.compraToCompraDTO(compra);

        assertNotNull(compraDTO);
        assertEquals(200, compraDTO.getOrder_id());
        assertEquals(50.0, compraDTO.getTotal());
        assertEquals(date, compraDTO.getDate());
        assertNotNull(compraDTO.getProducts());
        assertEquals(1, compraDTO.getProducts().size());

        ProdutoDTO produtoDTO = compraDTO.getProducts().get(0);
        assertEquals(2, produtoDTO.getProduto_id());
        assertEquals(50.0, produtoDTO.getValue());
    }

    @Test
    void testCompraToCompraDTO_withNullProducts() {
        Date date = new Date();

        Compra compra = new Compra();
        compra.setOrder_id(201);
        compra.setTotal(60.0);
        compra.setDate(date);
        compra.setProducts(null);

        CompraDTO compraDTO = CompraMapper.compraToCompraDTO(compra);

        assertNotNull(compraDTO);
        assertEquals(201, compraDTO.getOrder_id());
        assertEquals(60.0, compraDTO.getTotal());
        assertEquals(date, compraDTO.getDate());
        assertNotNull(compraDTO.getProducts());
        assertTrue(compraDTO.getProducts().isEmpty());
    }
}
