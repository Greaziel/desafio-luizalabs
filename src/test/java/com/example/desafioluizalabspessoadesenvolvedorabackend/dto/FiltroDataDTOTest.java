package com.example.desafioluizalabspessoadesenvolvedorabackend.dto;

import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.FiltroDataDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class FiltroDataDTOTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testGettersAndSetters() {
        Date dataInicial = new Date();
        Date dataFinal = new Date();

        FiltroDataDTO dto = new FiltroDataDTO();
        dto.setDataInicial(dataInicial);
        dto.setDataFinal(dataFinal);

        assertEquals(dataInicial, dto.getDataInicial());
        assertEquals(dataFinal, dto.getDataFinal());
    }

    @Test
    void testAllArgsConstructor() {
        Date dataInicial = new Date();
        Date dataFinal = new Date();

        FiltroDataDTO dto = new FiltroDataDTO(dataInicial, dataFinal);

        assertEquals(dataInicial, dto.getDataInicial());
        assertEquals(dataFinal, dto.getDataFinal());
    }
}
