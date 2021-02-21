package com.ernestomoney.api.dto.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ClientIdInputDTO {
   
   @NotNull
   private Long id;
}
