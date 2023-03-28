package com.algaworks.algalog.domain.exception;

import com.algaworks.algalog.domain.exception.NegocioException;

public class NaoEncontradoException extends NegocioException {
    private static final long serialVersionUID = 1L;
    public NaoEncontradoException(String message) {
        super(message);
    }
}
