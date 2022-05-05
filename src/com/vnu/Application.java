package com.vnu;

import com.vnu.parser.*;
import com.vnu.scanner.Scanner;
import com.vnu.scanner.Token;
import com.vnu.scanner.TokenType;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Application {

    static boolean hadError = false;
    static boolean hadRuntimeError = false;

    private static final Interpreter interpreter = new Interpreter();

    public static void main(String[] args) throws IOException {
        if (args.length == 1) {
            runFile(args[0]);
        }
    }

    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
        if (hadError) System.exit(65);
        if (hadRuntimeError) System.exit(70);
    }

    private static void run(String source) {
        // scan các ký tự
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();
        for (Token token : tokens) {
            System.out.println(token);
        }

        // Truyền các ký tự scan được vào bộ parser để sinh ra các statement
        Parser parser = new Parser(tokens);
        List<Stmt> statements = parser.parse();

        if (hadError) return;

        // Thực hiện xử lý logic cho các statement
        interpreter.interpret(statements);
    }

    public static void error(int line, String message) {
        report(line, "", message);
    }

    private static void report(int line, String where,
                               String message) {
        System.err.println(
                "[line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }

    public static void error(Token token, String message) {
        if (token.getType() == TokenType.EOF) {
            report(token.getLine(), " at end", message);
        } else {
            report(token.getLine(), " at '" + token.getLexeme() + "'", message);
        }
    }

    public static void runtimeError(RuntimeError error) {
        System.err.println(error.getMessage() +
                "\n[line " + error.getToken().getLine() + "]");
        hadRuntimeError = true;
    }


}
