package com.ernestomoney.api.dto.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.ernestomoney.api.domain.model.StatusOrdemServico;

import lombok.Data;

@Data
public class OrdemServicoResDTO {
   
   private Long id;
   private ClienteResumoResDTO cliente;
   private String descricao;
   private BigDecimal preco;
   private StatusOrdemServico status;
   private OffsetDateTime dataAbertura;
   private OffsetDateTime dataFinalizacao;

}
