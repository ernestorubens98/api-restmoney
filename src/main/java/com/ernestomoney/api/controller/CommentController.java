package com.ernestomoney.api.controller;

import javax.validation.Valid;

import com.ernestomoney.api.domain.model.Comment;
import com.ernestomoney.api.domain.service.ManagementOrderServiceService;
import com.ernestomoney.api.dto.request.CommentInput;
import com.ernestomoney.api.dto.request.CommentModel;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders-service/{orderServiceId}/comments")
public class CommentController {

   @Autowired
   private ManagementOrderServiceService managementOrderService;

   @Autowired
   private ModelMapper modelMapper;
   
   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public CommentModel createComment(@PathVariable Long orderServiceId, 
         @Valid @RequestBody CommentInput commentInput) {
   
      Comment comment = managementOrderService.addComment(orderServiceId, commentInput.getDescription());
      return toModel(comment);
   }

   private CommentModel toModel(Comment comment) {
      return modelMapper.map(comment, CommentModel.class);
   }
}
