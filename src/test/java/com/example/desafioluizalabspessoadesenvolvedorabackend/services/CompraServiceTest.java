package com.example.desafioluizalabspessoadesenvolvedorabackend.services;

import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.CompraDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.ProdutoDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.UsuarioDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.mappers.UsuarioMapper;
import com.example.desafioluizalabspessoadesenvolvedorabackend.models.Usuario;
import com.example.desafioluizalabspessoadesenvolvedorabackend.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompraServiceTest {

    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private CompraService compraService;

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    private InputStream gerarArquivoSimulado() {
        String linha =
                "0000000070                              " +
                        "Palmer Prosacco" +
                        "00000007530000000003     " +
                        "1836.7420210308";
        return new ByteArrayInputStream(linha.getBytes(StandardCharsets.UTF_8));
    }

    @BeforeEach
    void setup() {
    }

    @Test
    void testProcessarArquivo() throws Exception {
        InputStream input = gerarArquivoSimulado();

        ArgumentCaptor<List<Usuario>> captor = ArgumentCaptor.forClass(List.class);

        when(usuarioMapper.usuarioDTOToUsuario(any())).thenAnswer(invocation -> {
            UsuarioDTO dto = invocation.getArgument(0);
            Usuario u = new Usuario();
            u.setUserId(dto.getUser_id());
            u.setName(dto.getName());
            return u;
        });

        when(usuarioRepository.saveAll(captor.capture())).thenReturn(new ArrayList<>());

        List<UsuarioDTO> resultado = compraService.processarArquivo(input);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());

        UsuarioDTO usuarioDTO = resultado.get(0);
        assertEquals(70, usuarioDTO.getUser_id());
        assertEquals("Palmer Prosacco", usuarioDTO.getName().trim());

        CompraDTO compra = usuarioDTO.getOrders().get(0);
        assertEquals(753, compra.getOrder_id());
        assertEquals(1836.74, compra.getTotal().doubleValue());
        assertEquals(sdf.parse("20210308"), compra.getDate());

        ProdutoDTO produto = compra.getProducts().get(0);
        assertEquals(3, produto.getProduto_id());
        assertEquals(1836.74, produto.getValue());
    }

    @Test
    void testConsultarUsuarios() {
        Usuario u = new Usuario();
        u.setUserId(1);
        u.setName("Maria");

        when(usuarioRepository.findAll()).thenReturn(List.of(u));
        when(usuarioMapper.usuarioToUsuarioDTO(u)).thenAnswer(inv -> {
            UsuarioDTO dto = new UsuarioDTO();
            dto.setUser_id(1);
            dto.setName("Maria");
            dto.setOrders(new ArrayList<>());
            return dto;
        });

        List<UsuarioDTO> usu = compraService.consultarUsuarios();
        assertEquals(1, usu.size());
        assertEquals("Maria", usu.get(0).getName());
    }

    @Test
    void testConsultarPedidosPorId() {
        Usuario u = new Usuario();
        u.setUserId(2);

        when(usuarioRepository.buscarPorOrderId(999)).thenReturn(List.of(u));
        when(usuarioMapper.usuarioToUsuarioDTO(u)).thenReturn(new UsuarioDTO());

        List<UsuarioDTO> usu = compraService.consultarPedidosPorId(999);
        assertEquals(1, usu.size());
    }

    @Test
    void testConsultarPedidosPorData() throws Exception {
        Date inicio = sdf.parse("20240101");
        Date fim = sdf.parse("20241231");

        Usuario u = new Usuario();
        u.setUserId(3);

        when(usuarioRepository.buscarPorDataCompra(inicio, fim)).thenReturn(List.of(u));
        when(usuarioMapper.usuarioToUsuarioDTO(u)).thenReturn(new UsuarioDTO());

        List<UsuarioDTO> usu = compraService.consultarPedidosPorData(inicio, fim);
        assertEquals(1, usu.size());
    }

    @Test
    void testProcessarArquivoDataInvalida() {
        // Monta uma linha com data inválida "2024-06-01" (com hífens)
        String linhaInvalida =
                "0000000070                              " +
                        "Palmer Prosacco" +
                        "00000007530000000003     " +
                        "1836.74" +
                        "      2024-06";

        InputStream input = new ByteArrayInputStream(linhaInvalida.getBytes(StandardCharsets.UTF_8));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            compraService.processarArquivo(input);
        });

        assertTrue(thrown.getMessage().contains("Erro ao converter data"));
    }
}
