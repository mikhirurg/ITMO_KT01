package expression;

public abstract class UnaryOperation extends BaseExpression
        implements CommonExpression {
    BaseExpression operand;

    String operator;

    private int hash;

    UnaryOperation(BaseExpression operand, String operator) {
        this.operand = operand;
        this.operator = operator;
        int prime = 13;
        hash = operand.hashCode()*prime+operator.hashCode();
        setConstExpr(operand.isConstExpr());
    }

    @Override
    public String toString() {
        return "(" + operator + " " + operand.toString() + ")";
    }

    public String getOperator() {
        return operator;
    }

    @Override
    public int hashCode() {
        return hash;
    }

    @Override
    public String toMiniString() {
        String s = operand.toMiniString();
        return  getOperator()+ " " +
                (operand.getOperator().equals("const") ||
                        operand.getOperator().equals("variable")
                ? s : "(" + s + ")");
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
        UnaryOperation tmp = (UnaryOperation)obj;
        return getOperator().equals(tmp.getOperator());
    }
}
