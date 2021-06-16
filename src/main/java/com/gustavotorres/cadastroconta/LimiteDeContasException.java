package com.gustavotorres.cadastroconta;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INSUFFICIENT_STORAGE)
public class LimiteDeContasException extends RuntimeException {
    
    public LimiteDeContasException(String message) {
        super(message);
    }
}
