package com.example.desafioluizalabspessoadesenvolvedorabackend.controller;

import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.FiltroDataDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.UsuarioDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.services.CompraService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/desafio")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService compraService;

    @Operation(summary = "Faz upload de arquivo de pedidos")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<UsuarioDTO>> processarArquivo(@RequestParam("arquivo") MultipartFile file) throws Exception {
        return ResponseEntity.ok(compraService.processarArquivo(file.getInputStream()));
    }

    @Operation(summary = "Consulta todos os usuários e pedidos")
    @GetMapping(value = "/usuarios")
    public ResponseEntity<List<UsuarioDTO>> consultarTodos(){
        return ResponseEntity.ok(compraService.consultarUsuarios());
    }

    @Operation(summary = "Consulta pedidos de um usuário pelo ID")
    @GetMapping(value = "/usuarios/{id}")
    public ResponseEntity<List<UsuarioDTO>> consultarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(compraService.consultarPedidosPorId(id));
    }

    @Operation(summary = "Consulta pedidos de um usuário pelo ID")
    @PostMapping(value = "/usuarios/filtro-data")
    public ResponseEntity<?> consultarPorData(@RequestBody FiltroDataDTO filtro){
        List<UsuarioDTO> resultado = compraService.consultarPedidosPorData(filtro.getDataInicial(), filtro.getDataFinal());

        if (resultado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foram encontrados dados.");
        }

        return ResponseEntity.ok(resultado);

    }
}
