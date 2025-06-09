package com.example.desafioluizalabspessoadesenvolvedorabackend.controller;

import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.FiltroDataDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.UsuarioDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.services.CompraService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompraControllerTest {

    @Mock
    private CompraService compraService;

    @InjectMocks
    private CompraController compraController;

    // Remova o @BeforeEach

    @Test
    void testProcessarArquivo() throws Exception {
        String conteudo = "dados do arquivo";
        MultipartFile arquivo = new MockMultipartFile(
                "arquivo",
                "arquivo.txt",
                "text/plain",
                conteudo.getBytes(StandardCharsets.UTF_8)
        );

        List<UsuarioDTO> listaMock = List.of(new UsuarioDTO());
        when(compraService.processarArquivo(any())).thenReturn(listaMock);

        ResponseEntity<List<UsuarioDTO>> response = compraController.processarArquivo(arquivo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(listaMock, response.getBody());

        verify(compraService, times(1)).processarArquivo(any());
    }

    @Test
    void testConsultarTodos() {
        UsuarioDTO usuario = new UsuarioDTO(1, "João", List.of());
        List<UsuarioDTO> listaMock = List.of(usuario);

        when(compraService.consultarUsuarios()).thenReturn(listaMock);

        ResponseEntity<List<UsuarioDTO>> response = compraController.consultarTodos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(listaMock, response.getBody());

        verify(compraService, times(1)).consultarUsuarios();
    }

    @Test
    void testConsultarPorId() {
        int id = 1;
        List<UsuarioDTO> listaMock = List.of(new UsuarioDTO());
        when(compraService.consultarPedidosPorId(id)).thenReturn(listaMock);

        ResponseEntity<List<UsuarioDTO>> response = compraController.consultarPorId(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(listaMock, response.getBody());

        verify(compraService, times(1)).consultarPedidosPorId(id);
    }

    @Test
    void testConsultarTodosComFiltroData_RetornaDados() {
        Date dataInciial = new Date(20230101);
        Date dataFinal = new Date(20230131);
        FiltroDataDTO filtro = new FiltroDataDTO();
        filtro.setDataInicial(dataInciial);
        filtro.setDataFinal(dataFinal);

        List<UsuarioDTO> listaMock = List.of(new UsuarioDTO());
        when(compraService.consultarPedidosPorData(filtro.getDataInicial(), filtro.getDataFinal()))
                .thenReturn(listaMock);

        ResponseEntity<?> response = compraController.consultarTodos(filtro);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(listaMock, response.getBody());

        verify(compraService, times(1))
                .consultarPedidosPorData(filtro.getDataInicial(), filtro.getDataFinal());
    }

    @Test
    void testConsultarTodosComFiltroData_NaoEncontraDados() {
        FiltroDataDTO filtro = new FiltroDataDTO();
        Date dataInciial = new Date(20230101);
        Date dataFinal = new Date(20230131);
        filtro.setDataInicial(dataInciial);
        filtro.setDataFinal(dataFinal);

        when(compraService.consultarPedidosPorData(filtro.getDataInicial(), filtro.getDataFinal()))
                .thenReturn(Collections.emptyList());

        ResponseEntity<?> response = compraController.consultarTodos(filtro);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Não foram encontrados dados.", response.getBody());

        verify(compraService, times(1))
                .consultarPedidosPorData(filtro.getDataInicial(), filtro.getDataFinal());
    }
}
