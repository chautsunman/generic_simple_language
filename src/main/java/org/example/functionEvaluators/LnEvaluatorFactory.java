package org.example.functionEvaluators;

import org.example.FunctionEvaluator;
import org.example.FunctionEvaluatorFactory;
import org.example.calculatorParser;

import java.util.List;

public class LnEvaluatorFactory implements FunctionEvaluatorFactory {
    public static final String NAME = "ln";

    @Override
    public FunctionEvaluator createEvaluator(List<calculatorParser.ExpressionContext> expressionContexts) {
        return new LnEvaluator(expressionContexts);
    }
}
