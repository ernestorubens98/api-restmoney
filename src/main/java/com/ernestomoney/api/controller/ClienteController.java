package com.ernestomoney.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.ernestomoney.api.domain.model.Cliente;
import com.ernestomoney.api.domain.repository.ClienteRepository;
import com.ernestomoney.api.domain.service.RegistrarClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
   
   @Autowired
   private ClienteRepository clienteRepository;

   @Autowired
   private RegistrarClienteService clienteService;

   
   @GetMapping
   public List<Cliente> listaTodosClientes() {
      return clienteRepository.findAll();
   }

   @GetMapping("/{id}")
   public ResponseEntity<Cliente> encontraUmCliente(@PathVariable Long id) {
      Optional<Cliente> cliente = clienteRepository.findById(id);

      if (cliente.isPresent()) {
         return ResponseEntity.ok(cliente.get());
      }
      return ResponseEntity.notFound().build();
   }

   @PostMapping
   @ResponseStatus(code = HttpStatus.CREATED)
   public Cliente criaCliente(@Valid @RequestBody Cliente cliente) {
      return clienteService.salvarCliente(cliente);
   }

   @PutMapping("/{id}")
   public ResponseEntity<Cliente> atualizaCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
      if (!clienteRepository.existsById(id)) {
         return ResponseEntity.notFound().build();
      }

      cliente.setId(id);
      cliente = clienteService.salvarCliente(cliente);
      return ResponseEntity.ok(cliente);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deletaCliente(@PathVariable Long id) {
      if (!clienteRepository.existsById(id)) {
         return ResponseEntity.notFound().build();
      }
      clienteService.deletarClienteById(id);
      return ResponseEntity.noContent().build();
   }

}
