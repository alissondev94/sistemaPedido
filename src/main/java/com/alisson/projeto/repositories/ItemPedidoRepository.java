package com.alisson.projeto.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alisson.projeto.domain.ItemPedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository <ItemPedido, Integer> {
    Optional<ItemPedido> findById(Integer id);
}
