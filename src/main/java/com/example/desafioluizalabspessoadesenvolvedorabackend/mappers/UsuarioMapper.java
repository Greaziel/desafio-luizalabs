package com.example.desafioluizalabspessoadesenvolvedorabackend.mappers;

import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.CompraDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.UsuarioDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.models.Compra;
import com.example.desafioluizalabspessoadesenvolvedorabackend.models.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {
    private final CompraMapper compraMapper = new CompraMapper();

    public Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO) {

        Usuario usuario = new Usuario();
        usuario.setUserId(usuarioDTO.getUser_id());
        usuario.setName(usuarioDTO.getName());

        if (usuarioDTO.getOrders() != null) {
            List<Compra> compras = usuarioDTO.getOrders().stream()
                    .map(CompraMapper::compraDTOToCompra)
                    .collect(Collectors.toList());

            usuario.setOrders(compras);

            compras.forEach(compra -> compra.setUsuario(usuario));
        } else {
            usuario.setOrders(new ArrayList<>());
        }

        return usuario;
    }

    public UsuarioDTO usuarioToUsuarioDTO(Usuario usuario) {

        UsuarioDTO dto = new UsuarioDTO();
        dto.setUser_id(usuario.getUserId());
        dto.setName(usuario.getName());

        if (usuario.getOrders() != null) {
            List<CompraDTO> comprasDTO = usuario.getOrders().stream()
                    .map(CompraMapper::compraToCompraDTO)
                    .collect(Collectors.toList());

            dto.setOrders(comprasDTO);
        } else {
            dto.setOrders(new ArrayList<>());
        }

        return dto;
    }

}
