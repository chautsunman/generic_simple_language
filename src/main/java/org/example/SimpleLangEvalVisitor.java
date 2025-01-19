package org.example;

public class SimpleLangEvalVisitor extends calculatorBaseVisitor<Double> {
    @Override
    public Double visitEquation(calculatorParser.EquationContext ctx) {
        return 0.0;
    }

    @Override
    public Double visitExpression(calculatorParser.ExpressionContext ctx) {
        double result = visit(ctx.multiplyingExpression(0));
        for (int i = 1; i < ctx.multiplyingExpression().size(); i++) {
            if (ctx.PLUS(i - 1) != null) {
                result += visit(ctx.multiplyingExpression(i));
            } else if (ctx.MINUS(i - 1) != null) {
                result -= visit(ctx.multiplyingExpression(i));
            }
        }
        return result;
    }

    @Override
    public Double visitMultiplyingExpression(calculatorParser.MultiplyingExpressionContext ctx) {
        double result = visit(ctx.powExpression(0));
        for (int i = 1; i < ctx.powExpression().size(); i++) {
            if (ctx.TIMES(i - 1) != null) {
                result *= visit(ctx.powExpression(i));
            } else if (ctx.DIV(i - 1) != null) {
                result /= visit(ctx.powExpression(i));
            }
        }
        return result;
    }

    @Override
    public Double visitPowExpression(calculatorParser.PowExpressionContext ctx) {
        double result = visit(ctx.signedAtom(0));
        for (int i = 1; i < ctx.signedAtom().size(); i++) {
            result = Math.pow(result, visit(ctx.signedAtom(i)));
        }
        return result;
    }

    @Override
    public Double visitSignedAtom(calculatorParser.SignedAtomContext ctx) {
        if (ctx.PLUS() != null) {
            return visit(ctx.signedAtom());
        } else if (ctx.MINUS() != null) {
            return -visit(ctx.signedAtom());
        } else if (ctx.func_() != null) {
            return visit(ctx.func_());
        } else {
            return visit(ctx.atom());
        }
    }

    @Override
    public Double visitAtom(calculatorParser.AtomContext ctx) {
        if (ctx.scientific() != null) {
            return visit(ctx.scientific());
        } else if (ctx.variable() != null) {
            return visit(ctx.variable());
        } else if (ctx.constant() != null) {
            return visit(ctx.constant());
        } else {
            return visit(ctx.expression());
        }
    }

    @Override
    public Double visitScientific(calculatorParser.ScientificContext ctx) {
        return Double.parseDouble(ctx.getText());
    }

    @Override
    public Double visitConstant(calculatorParser.ConstantContext ctx) {
        if (ctx.PI() != null) {
            return Math.PI;
        } else if (ctx.EULER() != null) {
            return Math.E;
        } else if (ctx.I() != null) {
            return 0.0;
        } else {
            return 0.0;
        }
    }

    @Override
    public Double visitVariable(calculatorParser.VariableContext ctx) {
        return 0.0;
    }

    @Override
    public Double visitFunc_(calculatorParser.Func_Context ctx) {
        return 0.0;
    }

    @Override
    public Double visitFuncname(calculatorParser.FuncnameContext ctx) {
        return 0.0;
    }

    @Override
    public Double visitRelop(calculatorParser.RelopContext ctx) {
        return 0.0;
    }
}
