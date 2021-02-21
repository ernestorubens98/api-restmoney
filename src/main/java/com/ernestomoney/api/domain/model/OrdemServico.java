package com.ernestomoney.api.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
// import javax.validation.groups.ConvertGroup;
// import javax.validation.groups.Default;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// import com.ernestomoney.api.domain.ValidationGroups;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdemServico {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   // Se não colocar o atributo que irá fazer o join, por padrao ja pega o Id de Cliente
   @Valid
   // @ConvertGroup(from = Default.class, to = ValidationGroups.ClientId.class)
   @NotNull
   @ManyToOne
   private Cliente cliente;

   @NotBlank
   @Column(name = "descricao_ordem")
   private String descricao;

   @NotNull
   private BigDecimal preco;

   // Por ter adicionado a camada de DTO, não precisa coloca readonly na classe
   // @JsonProperty(access = Access.READ_ONLY)
   @Enumerated(EnumType.STRING)
   private StatusOrdemServico status;
   
   // @JsonProperty(access = Access.READ_ONLY)
   private OffsetDateTime dataAbertura;

   // @JsonProperty(access = Access.READ_ONLY)
   private OffsetDateTime dataFinalizacao;

   @OneToMany(mappedBy = "ordemServico") // O nome do atributo que coloquei no model Comentario
   private List<Comentario> comentarios = new ArrayList<>();

}
