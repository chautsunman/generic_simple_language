package org.example;

public interface FunctionEvaluator {
    EvalRes eval(calculatorBaseVisitor<EvalRes> visitor);
}
