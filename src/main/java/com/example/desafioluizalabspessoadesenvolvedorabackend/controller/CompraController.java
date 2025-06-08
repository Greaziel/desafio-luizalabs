package com.example.desafioluizalabspessoadesenvolvedorabackend.controller;

import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.CompraDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.UsuarioDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.services.CompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService compraService;

    @PostMapping(value = "/processar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UsuarioDTO> processarArquivo(@RequestParam("arquivo") MultipartFile file) throws Exception {
        return compraService.consultarPedidos(file.getInputStream());
    }
}
