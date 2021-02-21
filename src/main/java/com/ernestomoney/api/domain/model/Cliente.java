package com.ernestomoney.api.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
public class Cliente {
   
   //@NotNull(groups = ValidationGroups.ClientId.class) Dentro de Ordem de Serviço eu anoto o client id como @Valid e substituo o grupo padrao, para n ter conflito quando for criar um cliente e pedir id
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   // Posso colocar no atributo o name que quiser, e especificar dentro de column o nome do atributo no BD
   @NotBlank
   @Size(max = 60)
   @Column(name = "nome_cliente")
   private String name;
   
   @NotBlank
   @Email
   @Size(max = 60)
   @Column(name = "email_cliente")
   private String email;

   @NotBlank
   @Size(max = 20)
   @Column(name = "telefone_cliente")
   private String telefone;

}
