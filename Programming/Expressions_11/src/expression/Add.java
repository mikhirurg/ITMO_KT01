package expression;

public class Add extends BinaryOperation {
    Add(GenericExpression left, GenericExpression right) {
        super(left, right, '+');
    }

    @Override
    public int evaluate(int x) {
        return super.left.evaluate(x) + super.right.evaluate(x);
    }

    @Override
    public double evaluate(double x) {
        return super.left.evaluate(x) + super.right.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return super.left.evaluate(x,y,z) + super.right.evaluate(x,y,z);
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public boolean isCommutative() {
        return true;
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
