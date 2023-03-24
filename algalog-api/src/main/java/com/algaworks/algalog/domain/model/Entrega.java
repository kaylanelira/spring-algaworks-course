package com.algaworks.algalog.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Entrega {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    private Cliente cliente;
    @Embedded // impede que todos os dados de destinatarios estejam na tabela de entrega
    private Destinatario destinatario;
    private BigDecimal taxa;
    @Enumerated(EnumType.STRING)
    private StatusEntrega status;
    private LocalDateTime dataPedido;
    private LocalDateTime dataFinalizacao;
}
