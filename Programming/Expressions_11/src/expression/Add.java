package expression;

public class Add extends BinaryOperation {
    Add(BaseExpression left, BaseExpression right) {
        super(left, right, '+');
    }

    @Override
    protected int calculate(int x) {
        return super.left.evaluate(x) + super.right.evaluate(x);
    }

    @Override
    protected double calculate(double x) {
        return super.left.evaluate(x) + super.right.evaluate(x);
    }

    @Override
    protected int calculate(int x, int y, int z) {
        return super.left.evaluate(x,y,z) + super.right.evaluate(x,y,z);
    }
    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public boolean isNotCommutative() {
        return false;
    }

    @Override
    public String toMiniString() {
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

}
