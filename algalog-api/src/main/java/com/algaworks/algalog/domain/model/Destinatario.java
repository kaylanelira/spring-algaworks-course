package com.algaworks.algalog.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Destinatario {
    @Column(name = "destinatario_nome")
    private String nome;
    @Column(name = "destinatario_logradour")
    private String logadouro;
    @Column(name = "destinatario_complemento")
    private String complemento;
    @Column(name = "destinatario_bairro")
    private String bairro;
    @Column(name = "destinatario_num")
    private String num;
}
