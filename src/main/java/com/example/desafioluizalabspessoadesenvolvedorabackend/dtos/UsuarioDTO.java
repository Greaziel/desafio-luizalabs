package com.example.desafioluizalabspessoadesenvolvedorabackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Integer user_id;
    private String name;
    private List<CompraDTO> orders;
}
