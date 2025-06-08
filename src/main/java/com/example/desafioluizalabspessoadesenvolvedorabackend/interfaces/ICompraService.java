package com.example.desafioluizalabspessoadesenvolvedorabackend.interfaces;


import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.CompraDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.ProdutoDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.UsuarioDTO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public interface ICompraService {

    List<UsuarioDTO> consultarPedidos(InputStream inputStream) throws IOException;

    CompraDTO processarCompra(List<ProdutoDTO> produtos);


    List<CompraDTO> consultarPedidosPorId(Integer orderId);

    List<CompraDTO> consultarPedidosPorData(Date dataInicio, Date dataFim);

}
