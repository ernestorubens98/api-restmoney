package com.ernestomoney.api.domain.repository;

import com.ernestomoney.api.domain.model.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
   
}
