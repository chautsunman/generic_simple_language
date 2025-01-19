package org.example;

public class SimpleLangEvalVisitor extends calculatorBaseVisitor<EvalRes> {
    @Override
    public EvalRes visitEquation(calculatorParser.EquationContext ctx) {
        return EvalRes.newNum(0.0);
    }

    @Override
    public EvalRes visitExpression(calculatorParser.ExpressionContext ctx) {
        double result = visit(ctx.multiplyingExpression(0)).getNum();
        for (int i = 1; i < ctx.multiplyingExpression().size(); i++) {
            if (ctx.PLUS(i - 1) != null) {
                result += visit(ctx.multiplyingExpression(i)).getNum();
            } else if (ctx.MINUS(i - 1) != null) {
                result -= visit(ctx.multiplyingExpression(i)).getNum();
            }
        }
        return EvalRes.newNum(result);
    }

    @Override
    public EvalRes visitMultiplyingExpression(calculatorParser.MultiplyingExpressionContext ctx) {
        double result = visit(ctx.powExpression(0)).getNum();
        for (int i = 1; i < ctx.powExpression().size(); i++) {
            if (ctx.TIMES(i - 1) != null) {
                result *= visit(ctx.powExpression(i)).getNum();
            } else if (ctx.DIV(i - 1) != null) {
                result /= visit(ctx.powExpression(i)).getNum();
            }
        }
        return EvalRes.newNum(result);
    }

    @Override
    public EvalRes visitPowExpression(calculatorParser.PowExpressionContext ctx) {
        double result = visit(ctx.signedAtom(0)).getNum();
        for (int i = 1; i < ctx.signedAtom().size(); i++) {
            result = Math.pow(result, visit(ctx.signedAtom(i)).getNum());
        }
        return EvalRes.newNum(result);
    }

    @Override
    public EvalRes visitSignedAtom(calculatorParser.SignedAtomContext ctx) {
        if (ctx.PLUS() != null) {
            return visit(ctx.signedAtom());
        } else if (ctx.MINUS() != null) {
            return EvalRes.newNum(-visit(ctx.signedAtom()).getNum());
        } else if (ctx.func_() != null) {
            return visit(ctx.func_());
        } else {
            return visit(ctx.atom());
        }
    }

    @Override
    public EvalRes visitAtom(calculatorParser.AtomContext ctx) {
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
    public EvalRes visitScientific(calculatorParser.ScientificContext ctx) {
        return EvalRes.newNum(Double.parseDouble(ctx.getText()));
    }

    @Override
    public EvalRes visitConstant(calculatorParser.ConstantContext ctx) {
        if (ctx.PI() != null) {
            return EvalRes.newNum(Math.PI);
        } else if (ctx.EULER() != null) {
            return EvalRes.newNum(Math.E);
        } else if (ctx.I() != null) {
            return EvalRes.newNum(0.0);
        } else {
            return EvalRes.newNum(0.0);
        }
    }

    @Override
    public EvalRes visitVariable(calculatorParser.VariableContext ctx) {
        return EvalRes.newNum(0.0);
    }

    @Override
    public EvalRes visitFunc_(calculatorParser.Func_Context ctx) {
        return EvalRes.newFunc(visit(ctx.funcname()).getFuncName());
    }

    @Override
    public EvalRes visitFuncname(calculatorParser.FuncnameContext ctx) {
        return EvalRes.newFunc(ctx.getText());
    }

    @Override
    public EvalRes visitRelop(calculatorParser.RelopContext ctx) {
        return EvalRes.newNum(0.0);
    }
}
