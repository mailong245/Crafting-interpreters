package com.vnu.parser;

import com.vnu.scanner.Token;

public class RuntimeError extends RuntimeException {
    private final Token token;

    RuntimeError(Token token, String message) {
        super(message);
        this.token = token;
    }

    public Token getToken() {
        return token;
    }
}
