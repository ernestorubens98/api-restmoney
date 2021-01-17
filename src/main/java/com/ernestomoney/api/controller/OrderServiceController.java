package com.ernestomoney.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.ernestomoney.api.domain.model.OrderService;
import com.ernestomoney.api.domain.repository.OrderServiceRepository;
import com.ernestomoney.api.domain.service.ManagementOrderServiceService;
import com.ernestomoney.api.dto.request.OrderServiceInput;
import com.ernestomoney.api.dto.response.OrderServiceModel;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders-service")
public class OrderServiceController {
   
   @Autowired
   private ManagementOrderServiceService managementOrderService;

   @Autowired
   private OrderServiceRepository orderRepository;

   @Autowired
   private ModelMapper modelMapper;

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public OrderServiceModel createOrderService(@Valid @RequestBody OrderServiceInput orderServiceInput) {
      OrderService orderService = toEntity(orderServiceInput);

      return toModel(managementOrderService.createOrder(orderService));
   }

   @GetMapping
   public List<OrderServiceModel> listAllOrdersService() {
      return toColletionModel(orderRepository.findAll());
   }

   @GetMapping("/{id}")
   public ResponseEntity<OrderServiceModel> findOrderServiceById(@PathVariable Long id) {
      Optional<OrderService> orderService = orderRepository.findById(id);

      if (orderService.isPresent()) {
         OrderServiceModel orderServiceModel = toModel(orderService.get());
         return ResponseEntity.ok(orderServiceModel);
      }

      return ResponseEntity.notFound().build();
   }

   // Converte uma Ordem de Serviço do Domain para o Modelo representational do mapper
   private OrderServiceModel toModel(OrderService orderService) {
      return modelMapper.map(orderService, OrderServiceModel.class);
   }

   // Retorna uma lista de Ordens de Serviço de Domain para o representacional do mapper
   private List<OrderServiceModel> toColletionModel(List<OrderService> ordersService) {
      return ordersService.stream()
         .map(orderService -> toModel(orderService))
         .collect(Collectors.toList());
   }

   // Converte uma Ordem de Serviço Representacional em uma ordem de serviço do Domain
   private OrderService toEntity(OrderServiceInput orderServiceInput) {
      return modelMapper.map(orderServiceInput, OrderService.class);
   }

}
