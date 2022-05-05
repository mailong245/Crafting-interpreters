package com.vnu.parser;

import com.vnu.scanner.Token;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    private final Map<String, Object> values = new HashMap<>();
    private final Environment enclosing;

    public Environment() {
        enclosing = null;
    }

    public Environment(Environment enclosing) {
        this.enclosing = enclosing;
    }

    public Object get(Token name) {
        if (values.containsKey(name.getLexeme())) {
            return values.get(name.getLexeme());
        }

        if (enclosing != null) return enclosing.get(name);

        throw new RuntimeError(name,
                "Undefined variable '" + name.getLexeme() + "'.");
    }


    public void define(String name, Object value) {
        values.put(name, value);
    }

    public Map<String, Object> getValues() {
        return values;
    }


    public void assign(Token name, Object value) {
        if (values.containsKey(name.getLexeme())) {
            values.put(name.getLexeme(), value);
            return;
        }

        if (enclosing != null) {
            enclosing.assign(name, value);
            return;
        }

        throw new RuntimeError(name,
                "Undefined variable '" + name.getLexeme() + "'.");
    }
}
