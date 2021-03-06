package com.ernestomoney.api.domain.repository;

import com.ernestomoney.api.domain.model.Comentario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
   
}
