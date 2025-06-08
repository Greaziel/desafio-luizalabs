package com.example.desafioluizalabspessoadesenvolvedorabackend.interfaces;


import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.CompraDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.UsuarioDTO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public interface ICompraService {

    List<UsuarioDTO> processarArquivo(InputStream inputStream) throws IOException;

    List<UsuarioDTO> consultarUsuarios();


    List<UsuarioDTO> consultarPedidosPorId(Integer orderId);

    List<UsuarioDTO> consultarPedidosPorData(Date dataInicio, Date dataFim);

}
