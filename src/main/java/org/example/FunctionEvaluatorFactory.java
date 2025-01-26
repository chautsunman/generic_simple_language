package org.example;

import java.util.List;

public interface FunctionEvaluatorFactory {
    FunctionEvaluator createEvaluator(List<calculatorParser.ExpressionContext> expressionContexts);
}
