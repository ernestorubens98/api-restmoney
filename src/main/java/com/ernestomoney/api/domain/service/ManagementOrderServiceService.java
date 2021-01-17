package com.ernestomoney.api.domain.service;

import java.time.OffsetDateTime;

import com.ernestomoney.api.domain.exception.BusinessException;
import com.ernestomoney.api.domain.model.Client;
import com.ernestomoney.api.domain.model.Comment;
import com.ernestomoney.api.domain.model.OrderService;
import com.ernestomoney.api.domain.model.StatusOrderService;
import com.ernestomoney.api.domain.repository.ClientRepository;
import com.ernestomoney.api.domain.repository.CommentRepository;
import com.ernestomoney.api.domain.repository.OrderServiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagementOrderServiceService {

   @Autowired
   private OrderServiceRepository orderRepository;

   @Autowired
   private ClientRepository clientRepository;

   @Autowired
   private CommentRepository commentRepository;
   
   public OrderService createOrder(OrderService orderService) {
      Client client = clientRepository.findById(orderService.getClient().getId())
         .orElseThrow(() -> new BusinessException("Cliente não encontrado."));

      orderService.setClient(client);
      orderService.setStatus(StatusOrderService.OPEN);
      orderService.setDateOpen(OffsetDateTime.now());

      return orderRepository.save(orderService);
   }

   public Comment addComment(Long orderServiceId, String description) {
      OrderService orderService = orderRepository.findById(orderServiceId)
         .orElseThrow(() -> new BusinessException("Ordem de serviço não encontrada."));

      Comment comment =  new Comment();
      comment.setSendDate(OffsetDateTime.now());
      comment.setDescription(description);
      comment.setOrderService(orderService);

      return commentRepository.save(comment);
   }
}