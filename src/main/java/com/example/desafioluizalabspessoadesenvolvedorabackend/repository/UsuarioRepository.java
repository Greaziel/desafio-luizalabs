package com.example.desafioluizalabspessoadesenvolvedorabackend.repository;

import com.example.desafioluizalabspessoadesenvolvedorabackend.models.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

    @Query("SELECT c.usuario FROM Compra c WHERE c.order_id = :orderId")
    List<Usuario> buscarPorOrderId(@Param("orderId") Integer orderId);

    @Query("SELECT c.usuario FROM Compra c WHERE c.date BETWEEN :inicio AND :fim")
    List<Usuario> buscarPorDataCompra(@Param("inicio") Date inicio, @Param("fim") Date fim);

}
