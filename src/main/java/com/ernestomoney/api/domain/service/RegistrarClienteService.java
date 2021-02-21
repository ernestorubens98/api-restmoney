package com.ernestomoney.api.domain.service;

import com.ernestomoney.api.domain.exception.NegocioException;
import com.ernestomoney.api.domain.model.Cliente;
import com.ernestomoney.api.domain.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegistrarClienteService {

   @Autowired
   private ClienteRepository clienteRepository;
   
   public Cliente salvarCliente(Cliente cliente) {
      Cliente clienteExiste = clienteRepository.findByEmail(cliente.getEmail());

      if (clienteExiste != null && !clienteExiste.equals(cliente)) {
         throw new NegocioException("Já existe um cliente com este e-mail.");
      }

      return clienteRepository.save(cliente);
   }

   public void deletarClienteById(Long id) {
      clienteRepository.deleteById(id);
   }
}
