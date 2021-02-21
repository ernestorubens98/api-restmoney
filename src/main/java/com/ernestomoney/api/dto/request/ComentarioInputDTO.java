package com.ernestomoney.api.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ComentarioInputDTO {

   @NotBlank
   private String descricao;   
}
