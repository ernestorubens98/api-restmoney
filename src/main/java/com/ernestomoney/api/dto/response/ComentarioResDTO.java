package com.ernestomoney.api.dto.response;

import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class ComentarioResDTO {
   
   private Long id;
   private String descricao;
   private OffsetDateTime dataEnvio;

}
