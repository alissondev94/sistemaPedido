package com.alisson.projeto.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alisson.projeto.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository <Pedido, Integer> {
    Optional<Pedido> findById(Integer id);
}
