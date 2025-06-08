package com.example.desafioluizalabspessoadesenvolvedorabackend.services;

import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.CompraDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.ProdutoDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.UsuarioDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.interfaces.ICompraService;
import com.example.desafioluizalabspessoadesenvolvedorabackend.mappers.UsuarioMapper;
import com.example.desafioluizalabspessoadesenvolvedorabackend.models.Usuario;
import com.example.desafioluizalabspessoadesenvolvedorabackend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CompraService implements ICompraService {

    private List<UsuarioDTO> usuarios = new ArrayList<>();
    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioDTO> processarArquivo(InputStream inputStream) throws IOException {
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
                    novo.setUser_id(id);
                    novo.setName(name);
                    novo.setOrders(new ArrayList<>());
                    return novo;
                });

                CompraDTO compra = usuario.getOrders().stream()
                        .filter(c -> c.getOrder_id().equals(orderId))
                        .findFirst()
                        .orElseGet(() -> {
                            CompraDTO novaCompra = new CompraDTO(orderId, 0.0, dataCompra, new ArrayList<>());
                            usuario.getOrders().add(novaCompra);
                            return novaCompra;
                        });

                compra.getProducts().add(produto);
                compra.setTotal(compra.getTotal() + value);
            }

        }

        this.usuarios = new ArrayList<>(usuariosMap.values());
        List<Usuario> usuariosConvertidos = usuarios.stream()
                .map(usuarioMapper::usuarioDTOToUsuario)
                .toList();
        usuarioRepository.saveAll(usuariosConvertidos);
        return this.usuarios;


    }

    @Override
    public List<UsuarioDTO> consultarUsuarios() {
        return usuarios = usuarioRepository.findAll()
                .stream().map(usuarioMapper::usuarioToUsuarioDTO).toList();
    }

    @Override
    public List<UsuarioDTO> consultarPedidosPorId(Integer orderId) {
        return usuarioRepository.buscarPorOrderId(orderId)
                .stream()
                .map(usuarioMapper::usuarioToUsuarioDTO)
                .toList();
    }

    @Override
    public List<UsuarioDTO> consultarPedidosPorData(Date dataInicio, Date dataFim) {
        return usuarios = usuarioRepository.buscarPorDataCompra(dataInicio, dataFim)
                .stream().map(usuarioMapper::usuarioToUsuarioDTO).toList();

    }

    private Date parseData(String data) {
        try {
            return new SimpleDateFormat("yyyyMMdd").parse(data);
        } catch (ParseException e) {
            throw new RuntimeException("Erro ao converter data: " + data, e);
        }
    }
}
