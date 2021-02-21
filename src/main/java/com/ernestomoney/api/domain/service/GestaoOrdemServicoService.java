package com.ernestomoney.api.domain.service;

import java.time.OffsetDateTime;

import com.ernestomoney.api.domain.exception.EntidadeNaoEncontradaException;
import com.ernestomoney.api.domain.exception.NegocioException;
import com.ernestomoney.api.domain.model.Cliente;
import com.ernestomoney.api.domain.model.Comentario;
import com.ernestomoney.api.domain.model.OrdemServico;
import com.ernestomoney.api.domain.model.StatusOrdemServico;
import com.ernestomoney.api.domain.repository.ClienteRepository;
import com.ernestomoney.api.domain.repository.ComentarioRepository;
import com.ernestomoney.api.domain.repository.OrdemServicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestaoOrdemServicoService {

   @Autowired
   private OrdemServicoRepository ordemServicoRepository;

   @Autowired
   private ClienteRepository clienteRepository;

   @Autowired
   private ComentarioRepository comentarioRepository;
   
   public OrdemServico criarOrdemServico(OrdemServico ordemServico) {
      Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
         .orElseThrow(() -> new NegocioException("Cliente não encontrado."));

      ordemServico.setCliente(cliente);
      ordemServico.setStatus(StatusOrdemServico.ABERTA);
      ordemServico.setDataAbertura(OffsetDateTime.now());

      return ordemServicoRepository.save(ordemServico);
   }


   public void finalizarOrdemServico(Long ordemServicoId) {
      OrdemServico ordemServico = buscar(ordemServicoId);

      if (!StatusOrdemServico.ABERTA.equals(ordemServico.getStatus())) {
         throw new NegocioException("Ordem de serviço não pode ser finalizada");
      }
      
      ordemServico.setStatus(StatusOrdemServico.FINALIZADA);
      ordemServico.setDataFinalizacao(OffsetDateTime.now());

   }
 

   public Comentario adicionarComentario (Long ordemServicoId, String descricao) {
      OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
         .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada."));

      Comentario comentario =  new Comentario();
      comentario.setDataEnvio(OffsetDateTime.now());
      comentario.setDescricao(descricao);
      comentario.setOrdemServico(ordemServico);

      return comentarioRepository.save(comentario);
   }


   public OrdemServico buscar(Long ordemServicoId) {
      return ordemServicoRepository.findById(ordemServicoId)
         .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada."));
   }
}