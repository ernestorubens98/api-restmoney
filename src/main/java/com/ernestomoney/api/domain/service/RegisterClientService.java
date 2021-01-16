package com.ernestomoney.api.domain.service;

import com.ernestomoney.api.domain.exception.BusinessException;
import com.ernestomoney.api.domain.model.Client;
import com.ernestomoney.api.domain.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegisterClientService {

   @Autowired
   private ClientRepository clientRepository;
   
   public Client saveClient(Client client) {
      Client clientExist = clientRepository.findByEmail(client.getEmail());

      if (clientExist != null && !clientExist.equals(client)) {
         throw new BusinessException("Já existe um cliente com este e-mail.");
      }

      return clientRepository.save(client);
   }

   public void deleteClientById(Long id) {
      clientRepository.deleteById(id);
   }
}
