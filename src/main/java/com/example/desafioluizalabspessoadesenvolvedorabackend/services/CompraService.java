package com.example.desafioluizalabspessoadesenvolvedorabackend.services;

import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.CompraDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.ProdutoDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.UsuarioDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.interfaces.ICompraService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class CompraService implements ICompraService {

    private final List<ProdutoDTO> produtosMock = new ArrayList<>();

    private static final AtomicInteger orderIdGenerator = new AtomicInteger(1);

    @Override
    public List<UsuarioDTO> consultarPedidos(InputStream inputStream) throws IOException {
        Map<Integer, UsuarioDTO> usuariosMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String linha;
            while ((linha = reader.readLine()) != null) {

                int userId = Integer.parseInt(linha.substring(0, 10).trim().replaceFirst("^0+(?!$)", ""));
                String name = linha.substring(10, 55).trim();
                int orderId = Integer.parseInt(linha.substring(55, 65).trim().replaceFirst("^0+(?!$)", ""));
                int productId = Integer.parseInt(linha.substring(65, 75).trim().replaceFirst("^0+(?!$)", ""));
                double value = Double.parseDouble(linha.substring(75, 87).trim());
                String dataStr = linha.substring(87, 95).trim();
                Date dataCompra = parseData(dataStr);

                ProdutoDTO produto = new ProdutoDTO(productId, value);

                UsuarioDTO usuario = usuariosMap.computeIfAbsent(userId, id -> {
                    UsuarioDTO novo = new UsuarioDTO();
                    novo.setUserId(id);
                    novo.setName(name);
                    CompraDTO novaCompra = new CompraDTO(orderId, 0.0, dataCompra, new ArrayList<>());
                    novo.setCompra(novaCompra);
                    return novo;
                });


                CompraDTO compra = usuario.getCompra();
                compra.getProdutos().add(produto);
                compra.setTotal(compra.getTotal() + value);

            }
        }

        return new ArrayList<>(usuariosMap.values());

    }

    @Override
    public CompraDTO processarCompra(List<ProdutoDTO> produtos) {
        Double total = produtos.stream()
                .mapToDouble(p -> {
                    try {
                        return p.getValue();
                    } catch (NumberFormatException e) {
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

    private Date parseData(String yyyymmdd) {
        try {
            return new SimpleDateFormat("yyyyMMdd").parse(yyyymmdd);
        } catch (ParseException e) {
            throw new RuntimeException("Erro ao converter data: " + yyyymmdd, e);
        }
    }
}
