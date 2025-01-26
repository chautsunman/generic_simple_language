package org.example;

public class EvalRes {
    public enum Type {
        NUMERIC,
        STRING,
        BOOLEAN,
        FUNCTION
    }

    private final Type type;
    private final double num;
    private final String str;
    private final boolean bool;
    private final String funcName;

    private EvalRes(Type type, double num, String str, boolean bool, String funcName) {
        this.type = type;
        this.num = num;
        this.str = str;
        this.bool = bool;
        this.funcName = funcName;
    }

    public static EvalRes newNum(double num) {
        return new EvalRes(Type.NUMERIC, num, null, false, null);
    }

    public static EvalRes newStr(String str) {
        return new EvalRes(Type.STRING, Double.NaN, str, false, null);
    }

    public static EvalRes newBool(boolean bool) {
        return new EvalRes(Type.BOOLEAN, Double.NaN, null, bool, null);
    }

    public static EvalRes newFunc(String funcName) {
        return new EvalRes(Type.FUNCTION, Double.NaN, null, false, funcName);
    }

    public boolean isType(Type type) {
        return this.type == type;
    }

    public Object getVal() {
        if (type == Type.NUMERIC) {
            return num;
        } else if (type == Type.STRING) {
            return str;
        } else if (type == Type.BOOLEAN) {
            return bool;
        } else if (type == Type.FUNCTION) {
            return funcName;
        }
        return null;
    }

    public boolean isNumeric() {
        return type == Type.NUMERIC;
    }

    public double getNum() {
        if (type != Type.NUMERIC) {
            throw new UnsupportedOperationException();
        }
        return num;
    }

    public boolean isString() {
        return type == Type.STRING;
    }

    public String getStr() {
        if (type != Type.STRING) {
            throw new UnsupportedOperationException();
        }
        return str;
    }

    public boolean isBoolean() {
        return type == Type.BOOLEAN;
    }

    public boolean isBool() {
        if (type != Type.BOOLEAN) {
            throw new UnsupportedOperationException();
        }
        return bool;
    }

    public boolean isFunc() {
        return type == Type.FUNCTION;
    }

    public String getFuncName() {
        if (type != Type.FUNCTION) {
            throw new UnsupportedOperationException();
        }
        return funcName;
    }
}
