package com.vnu.scanner;

public enum TokenType {
    PROGRAM, STATEMENTS, STATEMENT, DECL, TYPE,
    ASSIGMENT, EXP, LOOP, ID,

    DO,
    BANG, BANG_EQUAL,
    EQUAL, EQUAL_EQUAL,
    GREATER, GREATER_EQUAL,
    LESS, LESS_EQUAL,
    LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE, RIGHT_BRACE,
    COMMA, DOT, MINUS, PLUS, SEMICOLON, SLASH, STAR, POWER,
    NOT, NOT_EQUAL,
    IDENTIFIER, STRING, NUMBER,
    AND, CLASS, ELSE, FALSE, FOR, IF, OR,
    PRINT, RETURN, THIS, TRUE, VAR, WHILE, FUN, NIL,
    SUPER,
    EOF
}
