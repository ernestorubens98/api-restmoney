package com.ernestomoney.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.ernestomoney.api.domain.model.Client;
import com.ernestomoney.api.domain.repository.ClientRepository;
import com.ernestomoney.api.domain.service.RegisterClientService;

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
@RequestMapping("/clients")
public class ClientController {
   
   @Autowired
   private ClientRepository clientRepository;

   @Autowired
   private RegisterClientService clientService;

   @GetMapping
   public List<Client> listAllClients() {
      return clientRepository.findAll();
   }

   @GetMapping("/{id}")
   public ResponseEntity<Client> findOneClient(@PathVariable Long id) {
      Optional<Client> client = clientRepository.findById(id);

      if (client.isPresent()) {
         return ResponseEntity.ok(client.get());
      }
      return ResponseEntity.notFound().build();
   }

   @PostMapping
   @ResponseStatus(code = HttpStatus.CREATED)
   public Client createClient(@Valid @RequestBody Client client) {
      return clientService.saveClient(client);
   }

   @PutMapping("/{id}")
   public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
      if (!clientRepository.existsById(id)) {
         return ResponseEntity.notFound().build();
      }

      client.setId(id);
      client = clientService.saveClient(client);
      return ResponseEntity.ok(client);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
      if (!clientRepository.existsById(id)) {
         return ResponseEntity.notFound().build();
      }
      clientService.deleteClientById(id);
      return ResponseEntity.noContent().build();
   }

}
