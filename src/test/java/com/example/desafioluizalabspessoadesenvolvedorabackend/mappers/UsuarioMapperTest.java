package com.example.desafioluizalabspessoadesenvolvedorabackend.mappers;

import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.CompraDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.ProdutoDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.UsuarioDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.models.Compra;
import com.example.desafioluizalabspessoadesenvolvedorabackend.models.Produto;
import com.example.desafioluizalabspessoadesenvolvedorabackend.models.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioMapperTest {

    private final UsuarioMapper mapper = new UsuarioMapper();

    @Test
    void testUsuarioDTOToUsuario() {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setProduto_id(1);
        produtoDTO.setValue(99.99);

        CompraDTO compraDTO = new CompraDTO();
        compraDTO.setOrder_id(100);
        compraDTO.setTotal(99.99);
        compraDTO.setDate(new Date());
        compraDTO.setProducts(List.of(produtoDTO));

        UsuarioDTO dto = new UsuarioDTO();
        dto.setUser_id(10);
        dto.setName("Jose");
        dto.setOrders(List.of(compraDTO));

        Usuario usuario = mapper.usuarioDTOToUsuario(dto);

        assertNotNull(usuario);
        assertEquals(10, usuario.getUserId());
        assertEquals("Jose", usuario.getName());
        assertNotNull(usuario.getOrders());
        assertEquals(1, usuario.getOrders().size());

        Compra compra = usuario.getOrders().get(0);
        assertEquals(usuario, compra.getUsuario());
        assertEquals(100, compra.getOrder_id());
        assertEquals(99.99, compra.getTotal());
        assertEquals(1, compra.getProducts().size());
    }

    @Test
    void testUsuarioToUsuarioDTO() {
        Produto produto = new Produto();
        produto.setProduct_id(2);
        produto.setValue(49.99);

        Compra compra = new Compra();
        compra.setOrder_id(200);
        compra.setTotal(49.99);
        compra.setDate(new Date());
        compra.setProducts(List.of(produto));

        Usuario usuario = new Usuario();
        usuario.setUserId(20);
        usuario.setName("Luiza");
        usuario.setOrders(List.of(compra));
        compra.setUsuario(usuario);

        UsuarioDTO dto = mapper.usuarioToUsuarioDTO(usuario);

        assertNotNull(dto);
        assertEquals(20, dto.getUser_id());
        assertEquals("Luiza", dto.getName());
        assertNotNull(dto.getOrders());
        assertEquals(1, dto.getOrders().size());

        CompraDTO compraDTO = dto.getOrders().get(0);
        assertEquals(200, compraDTO.getOrder_id());
        assertEquals(49.99, compraDTO.getTotal());
        assertEquals(1, compraDTO.getProducts().size());
    }

    @Test
    void testUsuarioDTOToUsuarioComOrdersNulos() {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setUser_id(30);
        dto.setName("Sem Compras");
        dto.setOrders(null);

        Usuario usuario = mapper.usuarioDTOToUsuario(dto);

        assertNotNull(usuario);
        assertEquals(30, usuario.getUserId());
        assertEquals("Sem Compras", usuario.getName());
        assertNotNull(usuario.getOrders());
        assertTrue(usuario.getOrders().isEmpty());
    }

    @Test
    void testUsuarioToUsuarioDTOComOrdersNulos() {
        Usuario usuario = new Usuario();
        usuario.setUserId(40);
        usuario.setName("Sem Compras");
        usuario.setOrders(null);

        UsuarioDTO dto = mapper.usuarioToUsuarioDTO(usuario);

        assertNotNull(dto);
        assertEquals(40, dto.getUser_id());
        assertEquals("Sem Compras", dto.getName());
        assertNotNull(dto.getOrders());
        assertTrue(dto.getOrders().isEmpty());
    }
}
