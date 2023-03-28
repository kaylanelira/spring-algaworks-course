package com.algaworks.algalog.api.controller;

import java.util.List;

import com.algaworks.algalog.api.assembler.ClienteAssembler;
import com.algaworks.algalog.api.model.ClienteModel;
import com.algaworks.algalog.api.model.input.ClienteInput;
import com.algaworks.algalog.domain.service.CatalogoClienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	private ClienteRepository clienteRepository;
	private CatalogoClienteService catalogoClienteService;
	private ClienteAssembler clienteAssembler;

	// Lista todos os clientes
	@GetMapping
	public List<ClienteModel> listar() {
		return clienteAssembler.toCollectionModel(clienteRepository.findAll());
	}

	// Buscando por Id retorna cliente info ou erro 404
	@GetMapping("/id/{clienteId}")
	public ResponseEntity<ClienteModel> buscar(@PathVariable Long clienteId) {
		return clienteRepository.findById(clienteId)
				.map(cliente -> ResponseEntity.ok(clienteAssembler.toModel(cliente)))
				.orElse(ResponseEntity.notFound().build());
	}

	// Adiciona cliente transformando JSON em objeto java
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteModel adicionar (@Valid @RequestBody ClienteInput clienteInput) {
		Cliente novoCliente = clienteAssembler.toEntity(clienteInput);
		Cliente clienteAdicionado = catalogoClienteService.salvar(novoCliente);

		return clienteAssembler.toModel(clienteAdicionado);
	}

	// Atualiza dados de um cliente
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar (@PathVariable Long clienteId, @Valid @RequestBody Cliente cliente) {
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}

		cliente.setId(clienteId);
		cliente = catalogoClienteService.salvar(cliente);

		return ResponseEntity.ok(cliente);
	}

	// Deletando dados de um cliente por id
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover (@PathVariable Long clienteId) {
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}

		catalogoClienteService.excluir(clienteId);

		return ResponseEntity.noContent().build();
	}
}