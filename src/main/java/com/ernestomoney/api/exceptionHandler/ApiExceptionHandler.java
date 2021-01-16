package com.ernestomoney.api.exceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.ernestomoney.api.domain.exception.BusinessException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

   @Autowired
   private MessageSource messageSource;

   @ExceptionHandler(BusinessException.class)
   public ResponseEntity<Object> handleBusiness(BusinessException ex, WebRequest request) {
      var status = HttpStatus.BAD_REQUEST;

      var problem = new Problem();
      problem.setStatus(status.value());
      problem.setTitle(ex.getMessage());
      problem.setDateHour(LocalDateTime.now());

      return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
   }
   
   @Override
   protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
         HttpHeaders headers, HttpStatus status, WebRequest request) {
      
      var problem = new Problem();
      var campos = new ArrayList<Problem.Campo>();

      for (ObjectError error : ex.getBindingResult().getAllErrors()) {
         String name = ((FieldError) error).getField();
         String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());

         campos.add(new Problem.Campo(name, message));
      }

      problem.setStatus(status.value());
      problem.setTitle("Um ou mais campos estão inválidos.");
      problem.setDateHour(LocalDateTime.now());

      problem.setCampos(campos);

      return super.handleExceptionInternal(ex, problem, headers, status, request);
   }
}
