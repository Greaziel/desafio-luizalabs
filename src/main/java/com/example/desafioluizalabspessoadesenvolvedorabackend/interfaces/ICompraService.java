package com.example.desafioluizalabspessoadesenvolvedorabackend.interfaces;


import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.CompraDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.ProdutoDTO;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public interface ICompraService {

    List<CompraDTO> consultarPedidos(InputStream inputStream);

    CompraDTO processarCompra(List<ProdutoDTO> produtos);


    List<CompraDTO> consultarPedidosPorId(Integer orderId);

    List<CompraDTO> consultarPedidosPorData(Date dataInicio, Date dataFim);

}
