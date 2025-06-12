package com.alisson.projeto.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alisson.projeto.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Integer>{

	Optional<Cliente> findById(Integer id);

}
