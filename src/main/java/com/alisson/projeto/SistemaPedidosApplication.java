package com.alisson.projeto;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alisson.projeto.domain.Categoria;
import com.alisson.projeto.domain.Cidade;
import com.alisson.projeto.domain.Cliente;
import com.alisson.projeto.domain.Endereco;
import com.alisson.projeto.domain.Estado;
import com.alisson.projeto.domain.ItemPedido;
import com.alisson.projeto.domain.Pagamento;
import com.alisson.projeto.domain.PagamentoComBoleto;
import com.alisson.projeto.domain.PagamentoComCartao;
import com.alisson.projeto.domain.Pedido;
import com.alisson.projeto.domain.Produto;
import com.alisson.projeto.domain.enums.EstadoPagamento;
import com.alisson.projeto.domain.enums.TipoCliente;
import com.alisson.projeto.repositories.CategoriaRepository;
import com.alisson.projeto.repositories.CidadeRepository;
import com.alisson.projeto.repositories.ClienteRepository;
import com.alisson.projeto.repositories.EnderecoRepository;
import com.alisson.projeto.repositories.EstadoRepository;
import com.alisson.projeto.repositories.ItemPedidoRepository;
import com.alisson.projeto.repositories.PagamentoRepository;
import com.alisson.projeto.repositories.PedidoRepository;
import com.alisson.projeto.repositories.ProdutoRepository;

@SpringBootApplication
public class SistemaPedidosApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

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

        Estado est1 = new Estado(null, "Bahia");
        Estado est2 = new Estado(null, "Rio de Janeiro");

        Cidade c1 = new Cidade(null, "Salvador", est1);
        Cidade c2 = new Cidade(null, "Rio de Janeiro", est2);
        Cidade c3 = new Cidade(null, "Feira de Santana", est1);

        est1.getCidades().addAll(Arrays.asList(c1, c3));
        est2.getCidades().addAll(Arrays.asList(c2));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

        Cliente cli1 = new Cliente(null, "Alisson Paixão", "Alissonpx@gmail.com", "012.345.678-90", TipoCliente.PESSOAFISICA);
        cli1.getTelefones().addAll(Arrays.asList("71983852562", "71997052265"));

        Endereco e1 = new Endereco(null, "Jardim 2 de julho", "69", "casa", "Pituba", "41347200", cli1, c1);
        Endereco e2 = new Endereco(null, "copacabana", "100", "apt304", "caju", "4526873", cli1, c2);

        cli1.getEndereco().addAll(Arrays.asList(e1, e2));

        clienteRepository.save(cli1);
        enderecoRepository.saveAll(cli1.getEndereco());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(null, sdf.parse("19/06/2025 21:01"), cli1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("16/06/2025 20:41"), cli1, e2);

        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/06/2025 00:00"), null);
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoRepository.save(ped1);
        pedidoRepository.save(ped2);
        pagamentoRepository.save(pagto1);
        pagamentoRepository.save(pagto2);
        
        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
        
        ped1.getItens().addAll(Arrays.asList(ip1,ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));
        
        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));
        
        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
        		
        
    }
}
