package com.ernestomoney.api.domain.repository;

import java.util.List;

import com.ernestomoney.api.domain.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long> {
   List<Cliente> findByName(String name);
   List<Cliente> findByNameContaining(String name);
   Cliente findByEmail(String email);
}
