package com.algaworks.algalog.api.model;

import com.algaworks.algalog.domain.model.StatusEntrega;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
// MODELO DE REPRESENTAÇÃO DA ENTREGA
public class EntregaModel {
//    private ClienteModel clienteModel;
    private Long id;
    private ClienteResumoModel cliente;
    private String nomeCliente;
    private BigDecimal taxa;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;
    private StatusEntrega status;
    private DestinatarioModel destinatario;
}