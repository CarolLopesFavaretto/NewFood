package com.newfood.delivery.domain.exceptions.handler;

import lombok.Getter;

@Getter
public enum ErrorType {

    ENTITY_NOT_FOUND("/entidade-nao-encontrada", "Entidade não encontrada"),
    ENTITY_IN_USE("/entidade-em-uso", "Entidade em uso"),
    BUSINESS("/erro-negocio", "Violação de regra de negócio"),

    MESSAGE_INEXPLICABLE("/mensagem-inexplicavel", "Mensagem Inexplicavel");

    private String title;
    private String uri;

    ErrorType(String path, String title) {
        this.uri = "https://newfood.com.br" + path;
        this.title = title;
    }
}
