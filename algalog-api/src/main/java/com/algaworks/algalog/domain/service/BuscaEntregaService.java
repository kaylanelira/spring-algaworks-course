package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exception.NaoEncontradoException;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BuscaEntregaService {
    EntregaRepository entregaRepository;
    public Entrega buscar(Long entregaId) {
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new NaoEncontradoException(("Entrega não encontrada.")));
    }
}
