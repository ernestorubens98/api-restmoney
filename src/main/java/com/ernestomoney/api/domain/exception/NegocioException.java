package com.ernestomoney.api.domain.exception;

public class NegocioException extends RuntimeException {

   static final long serialVersionUID = 1L;

   public NegocioException(String message) {
      super(message);
   }
   
}
