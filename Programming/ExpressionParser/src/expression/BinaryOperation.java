package expression;

public abstract class BinaryOperation extends BaseExpression
        implements GenericExpression {
    BaseExpression left, right;

    char operator;

    BinaryOperation(BaseExpression left, BaseExpression right, char operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
        setConstExpr(left.isConstExpr() && right.isConstExpr());
    }

    @Override
    public String toString() {
        return "("+left.toString()+" "+operator+" "+right.toString()+")";
    }

    public char getOperator() {
        return operator;
    }

    @Override
    public int hashCode() {
        String str = this.toString();
        int prime = 31;
        int hash = 0;
        for (char c : str.toCharArray()) {
            hash += prime * c;
            prime *= prime;
        }
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
                    (isNotCommutative() || right.isNotCommutative())) {
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
}
