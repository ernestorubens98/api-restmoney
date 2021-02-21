package com.ernestomoney.api.dto.request;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrdemServicoInputDTO {

   @NotBlank
   private String descricao;

   @NotNull
   private BigDecimal preco;

   @Valid
   @NotNull
   private ClientIdInputDTO clienteId;
}
