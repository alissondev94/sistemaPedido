package com.alisson.projeto;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alisson.projeto.domain.Categoria;
import com.alisson.projeto.domain.Produto;
import com.alisson.projeto.repositories.CategoriaRepository;
import com.alisson.projeto.repositories.ProdutoRepository;

@SpringBootApplication
public class SistemaPedidosApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public static void main(String[] args) {
        SpringApplication.run(SistemaPedidosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Categoria cat1 = new Categoria(null, "Informatica");
        Categoria cat2 = new Categoria(null, "Escritorio");

        Produto p1 = new Produto(null, "Computador", 2000.00, Arrays.asList(cat1));
        Produto p2 = new Produto(null, "Impressora", 800.00, Arrays.asList(cat1, cat2));
        Produto p3 = new Produto(null, "Mouse", 80.00, Arrays.asList(cat1));

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().add(p2);

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
    }
}
