package com.ernestomoney.api.domain.exception;

public class BusinessException extends RuntimeException {

   static final long serialVersionUID = 1L;

   public BusinessException(String message) {
      super(message);
   }
   
}
