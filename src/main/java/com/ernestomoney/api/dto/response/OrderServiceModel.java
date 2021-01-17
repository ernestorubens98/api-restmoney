package com.ernestomoney.api.dto.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.ernestomoney.api.domain.model.StatusOrderService;

public class OrderServiceModel {
   
   private Long id;
   private ClientResumeModel client;
   private String description;
   private BigDecimal price;
   private StatusOrderService status;
   private OffsetDateTime dateOpen;
   private OffsetDateTime dateClosed;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public ClientResumeModel getClient() {
      return client;
   }

   public void setClient(ClientResumeModel client) {
      this.client = client;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public BigDecimal getPrice() {
      return price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public StatusOrderService getStatus() {
      return status;
   }

   public void setStatus(StatusOrderService status) {
      this.status = status;
   }

   public OffsetDateTime getDateOpen() {
      return dateOpen;
   }

   public void setDateOpen(OffsetDateTime dateOpen) {
      this.dateOpen = dateOpen;
   }

   public OffsetDateTime getDateClosed() {
      return dateClosed;
   }

   public void setDateClosed(OffsetDateTime dateClosed) {
      this.dateClosed = dateClosed;
   }

}
