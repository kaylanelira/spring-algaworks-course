package com.algaworks.algalog.api.assembler;

import com.algaworks.algalog.api.model.OcorrenciaModel;
import com.algaworks.algalog.domain.model.Ocorrencia;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

// RESPONSÁVEL POR CONVERSÕES ENTRE OCORRENCIA E OCORRENCIAMODEL
@AllArgsConstructor
@Component
public class OcorrenciaAssembler {
    private ModelMapper modelMapper;

    public List<OcorrenciaModel> toCollectionModel(List<Ocorrencia> ocorrenciaList) {
        return ocorrenciaList.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public OcorrenciaModel toModel(Ocorrencia ocorrencia) {
        return modelMapper.map(ocorrencia, OcorrenciaModel.class);
    }
}
