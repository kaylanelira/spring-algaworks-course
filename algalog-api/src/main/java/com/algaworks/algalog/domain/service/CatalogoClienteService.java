package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// CENTRALIZANDO AS REGRAS DE NEGÓCIO
@AllArgsConstructor
@Service
public class CatalogoClienteService {
    private ClienteRepository clienteRepository;

    // exception caso insira cliente que não existe
    public Cliente buscar (Long clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
    }

    // Transação = se houver algum erro dentro do método, todas as operações dele são descartadas
    @Transactional
    public Cliente salvar (Cliente cliente) {
        // impedir cadastro com email duplicado
        boolean emailDuplicado = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

        if (emailDuplicado) {
            throw new NegocioException("Já existe um cliente com este email.");
        }

        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir (Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}