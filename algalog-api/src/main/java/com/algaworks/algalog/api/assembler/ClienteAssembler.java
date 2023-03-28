package com.algaworks.algalog.api.assembler;

import com.algaworks.algalog.api.model.ClienteModel;
import com.algaworks.algalog.api.model.input.ClienteInput;
import com.algaworks.algalog.domain.model.Cliente;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

// RESPONSÁVEL POR CONVERSÕES ENTRE CLIENTE E CLIENTEMODEL
@AllArgsConstructor
@Component
public class ClienteAssembler {
    private ModelMapper modelMapper;

    public ClienteModel toModel(Cliente cliente) {
        return modelMapper.map(cliente, ClienteModel.class);
    }

    public List<ClienteModel> toCollectionModel(List<Cliente> clienteList) {
        return clienteList.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Cliente toEntity(ClienteInput clienteInput) {
        return modelMapper.map(clienteInput, Cliente.class);
    }
}
