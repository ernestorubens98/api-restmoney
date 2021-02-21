package com.ernestomoney.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.ernestomoney.api.domain.exception.EntidadeNaoEncontradaException;
import com.ernestomoney.api.domain.model.Comentario;
import com.ernestomoney.api.domain.model.OrdemServico;
import com.ernestomoney.api.domain.repository.OrdemServicoRepository;
import com.ernestomoney.api.domain.service.GestaoOrdemServicoService;
import com.ernestomoney.api.dto.request.ComentarioInputDTO;
import com.ernestomoney.api.dto.response.ComentarioResDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

   @Autowired
   private GestaoOrdemServicoService gestaoOrdemServicoService;

   @Autowired
   private OrdemServicoRepository ordemServicoRepository;

   @Autowired
   private ModelMapper modelMapper;

   @GetMapping
   public List<ComentarioResDTO> listar(@PathVariable Long ordemServicoId) {
      OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));

      return toCollectionDTO(ordemServico.getComentarios());
   }

   
   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public ComentarioResDTO adicionar(@PathVariable Long ordemServicoId, 
         @Valid @RequestBody ComentarioInputDTO comentarioInputDTO) {
   
      Comentario comentario = gestaoOrdemServicoService.adicionarComentario(ordemServicoId, comentarioInputDTO.getDescricao());
      return toDTO(comentario);
   }

   private ComentarioResDTO toDTO(Comentario comentario) {
      return modelMapper.map(comentario, ComentarioResDTO.class);
   }

   private List<ComentarioResDTO> toCollectionDTO(List<Comentario> comentarios) {
      return comentarios.stream()
            .map(comentario -> toDTO(comentario))
            .collect(Collectors.toList());
   }
}
