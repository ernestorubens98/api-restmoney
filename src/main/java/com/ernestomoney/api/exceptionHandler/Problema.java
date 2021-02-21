package com.ernestomoney.api.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Problema {
   
   private Integer status;
   private OffsetDateTime dataHora;
   private String titulo;
   private List<Campo> campos;

   public static class Campo {
      
      private String name;
      private String mensagem;

      public Campo(String name, String mensagem) {
         this.name = name;
         this.mensagem = mensagem;
      }

      public String getName() {
         return name;
      }

      public void setName(String name) {
         this.name = name;
      }

      public String getMensagem() {
         return mensagem;
      }

      public void setMensagem(String mensagem) {
         this.mensagem = mensagem;
      }
      
   }

   public Integer getStatus() {
      return status;
   }

   public void setStatus(Integer status) {
      this.status = status;
   }

   public OffsetDateTime getDateHour() {
      return dataHora;
   }

   public void setDateHour(OffsetDateTime dataHora) {
      this.dataHora = dataHora;
   }

   public String getTitle() {
      return titulo;
   }

   public void setTitle(String titulo) {
      this.titulo = titulo;
   }

   public List<Campo> getCampos() {
      return campos;
   }

   public void setCampos(List<Campo> campos) {
      this.campos = campos;
   }
   
}
