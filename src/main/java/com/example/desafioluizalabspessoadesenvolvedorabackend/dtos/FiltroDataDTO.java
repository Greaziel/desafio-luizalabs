package com.example.desafioluizalabspessoadesenvolvedorabackend.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FiltroDataDTO {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataInicial;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataFinal;

    public FiltroDataDTO() {
    }

    public FiltroDataDTO(Date dataInicial, Date dataFinal) {
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }
}
