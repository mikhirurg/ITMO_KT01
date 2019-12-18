package expression;

import java.util.HashMap;

public abstract class BaseExpression implements GenericExpression {
    static class Args {
        int [] args;

        public Args(int x) {
            args = new int[] {x};
        }

        public Args(int x, int y, int z) {
            args = new int[] {x, y, z};
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            return this.hashCode() == obj.hashCode();
        }

        @Override
        public int hashCode() {
            int hash = 0;
            int prime = 13;
            int localPrime = prime;
            for (int i : args) {
                hash += i * localPrime;
                localPrime *= prime;
            }
            return hash;
        }

        static Args of(int x) {
            return new Args(x);
        }
        static Args of(int x, int y, int z) {
            return new Args(x, y, z);
        }

    }
    private HashMap<Double, Double> dblCache = new HashMap<>();

    private HashMap<Args, Integer> intCache = new HashMap<>();

    private boolean constExpr = false;

    private boolean constExprEvaluated = false;

    private double dblConstVal;

    private int intConstVal;

    protected boolean isConstExpr() {
        return constExpr;
    }
    protected void setConstExpr(boolean val) {
        constExpr = val;
    }

    public void setDblConstVal(double dblConstVal) {
        this.dblConstVal = dblConstVal;
        constExprEvaluated = true;
        constExpr = true;
    }

    public void setIntConstVal(int intConstVal) {
        this.intConstVal = intConstVal;
        constExprEvaluated = true;
        constExpr = true;
    }

    public double getDblConstVal() {
        return dblConstVal;
    }

    public int getIntConstVal() {
        return intConstVal;
    }

    public boolean isConstExprEvaluated() {
        return constExprEvaluated;
    }

    protected int calculate(int x) {
        return x;
    }
    protected double calculate(double x) {
        return x;
    }

    protected int calculate(int x, int y, int z) {
        return 0;
    }


    @Override
    public int evaluate(int x) {
        if (isConstExpr()) {
            if (isConstExprEvaluated()) {
                return getIntConstVal();
            }
        }
        Args key = Args.of(x);
        if (intCache.containsKey(key)) {
            return intCache.get(key);
        }
        int val = calculate(x);
        intCache.put(key, val);

        if (isConstExpr()) {
            setIntConstVal(val);
        }
        return val;
    }

    @Override
    public double evaluate(double x) {
        if (isConstExpr()) {
            if (isConstExprEvaluated()) {
                return getDblConstVal();
            }
        }
        if (dblCache.containsKey(x)) {
            return dblCache.get(x);
        }

        double val = calculate(x);
        dblCache.put(x, val);

        if (isConstExpr()) {
            setDblConstVal(val);
        }
        return val;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if (isConstExpr()) {
            if (isConstExprEvaluated()) {
                return getIntConstVal();
            }
        }
        Args key = Args.of(x,y,z);
        if (intCache.containsKey(key)) {
            return intCache.get(key);
        }
        int val = calculate(x,y,z);
        intCache.put(key, val);

        if (isConstExpr()) {
            setIntConstVal(val);
        }
        return val;
    }
}
