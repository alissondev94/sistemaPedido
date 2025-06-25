package com.alisson.projeto.resources;

import com.alisson.projeto.domain.Pedido;
import com.alisson.projeto.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pedido> find(@PathVariable Integer id) {
    	Pedido obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }
}
