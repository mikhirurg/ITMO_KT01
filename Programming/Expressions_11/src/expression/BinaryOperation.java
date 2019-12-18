package expression;

import java.util.HashMap;

public abstract class BinaryOperation implements GenericExpression {
    GenericExpression left, right;
    private int hash;
    private final int prime = 7;
    private String operator;
    private boolean constant;
    private double doubleConstVal;
    private int intConstVal;
    private boolean isPreEval = false;

    private HashMap<Integer, Integer> varIntEval = new HashMap<>();
    private HashMap<Double, Double> varDoubleEval = new HashMap<>();

    BinaryOperation(GenericExpression left, GenericExpression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
        this.hash = left.hashCode() * prime + right.hashCode() * prime * prime + operator.hashCode();
        this.constant = left.isConst() & right.isConst();
        if (constant) {
            doubleConstVal = this.evaluate(0.0);
            intConstVal = this.evaluate(0);
        }
    }

    @Override
    public void PreEvaluated() {
        isPreEval = true;
    }

    @Override
    public boolean isPreEval(){
        return isPreEval;
    }

    @Override
    public int getIntConstVal() {
        return intConstVal;
    }

    @Override
    public double getDoubleConstVal() {
        return doubleConstVal;
    }

    @Override
    public double evaluate(double x) {
        if (isConst()) {
            return doubleConstVal;
        }
        return evaluate(x);
    }

    @Override
    public void addResult(int x, int val) {
        varIntEval.put(x,val);
    }

    @Override
    public void addResult(double x, double val) {
        varDoubleEval.put(x,val);
    }

    @Override
    public String toString() {
        return "("+left.toString()+" "+operator+" "+right.toString()+")";
    }

    public String getOperator() {
        return operator;
    }

    @Override
    public int hashCode() {
        return hash;
    }

    private void addBrackets(StringBuilder build, GenericExpression exp) {
        build.append("(");
        build.append(exp.toMiniString());
        build.append(")");
    }

    @Override
    public String toMiniString() {
        StringBuilder result = new StringBuilder();
        if (left.getPriority() < getPriority()) {
            addBrackets(result, left);
        } else {
            result.append(left.toMiniString());
        }
        result.append(" ").append(getOperator()).append(" ");
        if (getPriority() > right.getPriority()) {
            addBrackets(result, right);
        } else {
            if (getPriority() == right.getPriority() &&
                    (!isCommutative() || !right.isCommutative())) {
                addBrackets(result, right);
            } else {
                result.append(right.toMiniString());
            }
        }
        return result.toString();
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
    public boolean isConst() {
        return constant;
    }

}
