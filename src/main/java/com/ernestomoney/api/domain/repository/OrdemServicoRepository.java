package com.ernestomoney.api.domain.repository;

import com.ernestomoney.api.domain.model.OrdemServico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemServicoRepository extends JpaRepository <OrdemServico, Long>{
   
}
