package org.example;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
    public static void main(String[] args) {
        final String expressionStr = "1 + 2";
        final calculatorLexer lexer = new calculatorLexer(CharStreams.fromString(expressionStr));
        final CommonTokenStream tokens = new CommonTokenStream(lexer);
        final calculatorParser parser = new calculatorParser(tokens);
        final ParseTree tree = parser.expression();

        final SimpleLangEvalVisitor evalVisitor = new SimpleLangEvalVisitor();
        double res = evalVisitor.visit(tree);

        System.out.println(String.format("%s = %f", expressionStr, res));
    }
}