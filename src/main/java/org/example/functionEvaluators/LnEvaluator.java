package org.example.functionEvaluators;

import org.example.EvalRes;
import org.example.FunctionEvaluator;
import org.example.calculatorBaseVisitor;
import org.example.calculatorParser;

import java.util.List;

public class LnEvaluator implements FunctionEvaluator {
    final List<calculatorParser.ExpressionContext> expressionContexts;

    public LnEvaluator(List<calculatorParser.ExpressionContext> expressionContexts) {
        this.expressionContexts = expressionContexts;
    }

    @Override
    public EvalRes eval(calculatorBaseVisitor<EvalRes> visitor) {
        return EvalRes.newNum(Math.log(visitor.visit(expressionContexts.get(0)).getNum()));
    }
}
