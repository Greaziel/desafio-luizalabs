package com.example.desafioluizalabspessoadesenvolvedorabackend.mappers;

import com.example.desafioluizalabspessoadesenvolvedorabackend.dtos.UsuarioDTO;
import com.example.desafioluizalabspessoadesenvolvedorabackend.models.Usuario;

public class UsuarioMapper {
    private final CompraMapper compraMapper = new CompraMapper();

    public Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO) {

        Usuario usuario = new Usuario();
        usuario.setUserId(usuarioDTO.getUserId());
        usuario.setName(usuarioDTO.getName());
        usuario.setCompra(compraMapper.compraDTOToCompra(usuarioDTO.getCompra()));

        return usuario;
    }

    public UsuarioDTO usuarioToUsuarioDTO(Usuario usuario) {

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setUserId(usuario.getUserId());
        usuarioDTO.setName(usuario.getName());
        usuarioDTO.setCompra(compraMapper.compraToCompraDTO(usuario.getCompra()));

        return usuarioDTO;
    }

}
