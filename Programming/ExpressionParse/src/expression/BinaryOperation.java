package expression;

public abstract class BinaryOperation extends BaseExpression
        implements CommonExpression {
    BaseExpression left, right;

    String operator;
    private int hash;

    BinaryOperation(BaseExpression left, BaseExpression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
        setConstExpr(left.isConstExpr() && right.isConstExpr());
        int prime = 7;
        this.hash = left.hashCode() * prime + right.hashCode() * prime * prime + operator.hashCode();
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


    private void addBrackets(StringBuilder build, CommonExpression exp) {
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
                    (isNotCommutative() || right.isNotCommutative())) {
                addBrackets(result, right);
            } else {
                result.append(right.toMiniString());
            }
        }
        return result.toString();
    }

    protected String toMiniStringSimple() {
        StringBuilder result = new StringBuilder();
        if (left.getPriority() < getPriority()) {
            result.append("(");
            result.append(left.toMiniString());
            result.append(")");
        } else {
            result.append(left.toMiniString());
        }
        result.append(" ").append(getOperator()).append(" ");
        if (getPriority() > right.getPriority()) {
            result.append("(");
            result.append(right.toMiniString());
            result.append(")");
        } else {
            result.append(right.toMiniString());
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

}
