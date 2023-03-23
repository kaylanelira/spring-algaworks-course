package com.algaworks.algalog.api.exceptionhandler;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Controlador global
@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;
    // Setando mensagem específica para erros
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<Problem.Campo> campos = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nome = (((FieldError) error).getField());
            String mensagem =  messageSource.getMessage(error, LocaleContextHolder.getLocale());
            campos.add(new Problem.Campo(nome, mensagem));
        }

        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setDataHora(LocalDateTime.now());
        problem.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente");
        problem.setCampos(campos);
        return handleExceptionInternal(ex, problem, headers, status, request);
    }
}
