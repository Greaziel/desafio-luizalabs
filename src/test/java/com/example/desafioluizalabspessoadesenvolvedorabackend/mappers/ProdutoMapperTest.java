package com.example.desafioluizalabspessoadesenvolvedorabackend.mappers;

import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.ProdutoDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.models.Compra;
import com.example.desafioluizalabspessoadesenvolvedorabackend.models.Produto;
import com.example.desafioluizalabspessoadesenvolvedorabackend.models.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ProdutoMapperTest {

    @Test
    void testProdutoDtoToProduto() {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setProduto_id(123);
        produtoDTO.setValue(99.99);

        Produto produto = ProdutoMapper.produtoDtoToProduto(produtoDTO);

        assertNotNull(produto);
        assertEquals(123, produto.getProduct_id());
        assertEquals(99.99, produto.getValue());
    }

    @Test
    void testProdutoToProdutoDTO() {
        Produto produto = new Produto();
        produto.setProduct_id(456);
        produto.setValue(49.50);

        ProdutoDTO dto = ProdutoMapper.produtoToProdutoDTO(produto);

        assertNotNull(dto);
        assertEquals(456, dto.getProduto_id());
        assertEquals(49.50, dto.getValue());
    }

    @Test
    void testAllArgsContrutor(){
        Produto produto = new Produto(456, 46.50, new Compra());
        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto);
        List<Compra> compras = new ArrayList<>();
        Usuario usuario = new Usuario(5, "teste", compras);
        Compra compra = new Compra(1, 10.56, new Date(), new Usuario(), produtos);
        assertEquals(456, produto.getProduct_id());
        assertEquals(46.50, produto.getValue());
        assertEquals(1, compra.getOrder_id());
        assertEquals(5, usuario.getUserId());
    }
}
