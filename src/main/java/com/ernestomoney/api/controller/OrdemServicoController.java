package com.ernestomoney.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.ernestomoney.api.domain.model.OrdemServico;
import com.ernestomoney.api.domain.repository.OrdemServicoRepository;
import com.ernestomoney.api.domain.service.GestaoOrdemServicoService;
import com.ernestomoney.api.dto.request.OrdemServicoInputDTO;
import com.ernestomoney.api.dto.response.OrdemServicoResDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

   @Autowired
   private GestaoOrdemServicoService gestaoOrdemServicoService;

   @Autowired
   private OrdemServicoRepository ordemServicoRepository;

   @Autowired
   private ModelMapper modelMapper;

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public OrdemServicoResDTO criar (@Valid @RequestBody OrdemServicoInputDTO ordemServicoInputDTO) {
      OrdemServico ordemServico = toModel(ordemServicoInputDTO);

      return toDTO(gestaoOrdemServicoService.criarOrdemServico(ordemServico));
   }

   @GetMapping
   public List<OrdemServicoResDTO> listar () {
      return toCollectionDTO(ordemServicoRepository.findAll());
   }

   @GetMapping("/{id}")
   public ResponseEntity<OrdemServicoResDTO> buscarUma (@PathVariable Long id) {
      Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(id);

      if (ordemServico.isPresent()) {
         OrdemServicoResDTO ordemServicoResDTO = toDTO(ordemServico.get());
         return ResponseEntity.ok(ordemServicoResDTO);
      }

      return ResponseEntity.notFound().build();
   }


   @PutMapping("{ordemServicoId}/finalizacao")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void finalizar (@PathVariable Long ordemServicoId) {
      gestaoOrdemServicoService.finalizarOrdemServico(ordemServicoId);
   }


   // Retorna uma lista de Ordens de Serviço de Domain para o representacional do Mapper
   private List<OrdemServicoResDTO> toCollectionDTO(List<OrdemServico> ordensServico) {
      return ordensServico.stream()
            .map(ordemServico -> toDTO(ordemServico))
            .collect(Collectors.toList());
   }

   // Converte um DTO para um Model
   private OrdemServico toModel(OrdemServicoInputDTO ordemServicoInputDTO) {
      return modelMapper.map(ordemServicoInputDTO, OrdemServico.class);
   }

   // Converte um Model para DTO
   private OrdemServicoResDTO toDTO(OrdemServico ordemServico) {
      return modelMapper.map(ordemServico, OrdemServicoResDTO.class);
   }

}
