package com.example.desafioluizalabspessoadesenvolvedorabackend.services;

import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.CompraDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.ProdutoDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.interfaces.ICompraService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompraService implements ICompraService {

    private static List<ProdutoDTO> produtoDTO;

    private static CompraDTO compraDTO;

    @Override
    public List<CompraDTO> consultarPedidos(InputStream inputStream) {
        Map<Integer, List<ProdutoDTO>> pedidosPorOrdem = produtoDTO.stream()
                .collect(Collectors.groupingBy(ProdutoDTO::getProduto_id));

        List<CompraDTO> compras = new ArrayList<>();

        for (Map.Entry<Integer, List<ProdutoDTO>> entry : pedidosPorOrdem.entrySet()) {
            Integer orderId = entry.getKey();
            List<ProdutoDTO> itensPedido = entry.getValue();

            Double total = 10D;

            Date dataCompra = new Date(10102021);

            List<ProdutoDTO> produtos = itensPedido.stream()
                    .map(p -> new ProdutoDTO(p.getProduto_id(), (p.getValue())))
                    .collect(Collectors.toList());

            CompraDTO compra = new CompraDTO(orderId, total, dataCompra, produtos);
            compras.add(compra);
        }

        return compras;
    }

    @Override
    public CompraDTO processarCompra(List<ProdutoDTO> produtos) {
        Double total = produtos.stream()
                .mapToDouble(p -> {
                    try {
                        return p.getValue();
                    }catch (NumberFormatException e) {
                        return 0.0; // ou lançar exceção
                    }
                })
                .sum();

        return null;
    }

    @Override
    public List<CompraDTO> consultarPedidosPorId(Integer orderId) {
        return List.of();
    }

    @Override
    public List<CompraDTO> consultarPedidosPorData(Date dataInicio, Date dataFim) {
        return List.of();
    }
}
