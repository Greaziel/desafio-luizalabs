package com.example.desafioluizalabspessoadesenvolvedorabackend.controller;

import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.FiltroDataDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.UsuarioDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.services.CompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService compraService;

    @PostMapping(value = "/processar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<UsuarioDTO>> processarArquivo(@RequestParam("arquivo") MultipartFile file) throws Exception {
        return ResponseEntity.ok(compraService.processarArquivo(file.getInputStream()));
    }

    @GetMapping(value = "/consultar")
    public ResponseEntity<List<UsuarioDTO>> consultarTodos(){
        return ResponseEntity.ok(compraService.consultarUsuarios());
    }

    @GetMapping(value = "/consultar/{id}")
    public ResponseEntity<List<UsuarioDTO>> consultarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(compraService.consultarPedidosPorId(id));
    }

    @PostMapping(value = "/consultardata")
    public ResponseEntity<?> consultarTodos(@RequestBody FiltroDataDTO filtro){
        List<UsuarioDTO> resultado = compraService.consultarPedidosPorData(filtro.getDataInicial(), filtro.getDataFinal());

        if (resultado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foram encontrados dados.");
        }

        return ResponseEntity.ok(resultado);

    }
}
