package com.example.desafioluizalabspessoadesenvolvedorabackend.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiltroDataDTO {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataInicial;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataFinal;
}
