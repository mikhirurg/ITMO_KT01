package expression;

public class Multiply extends BinaryOperation {
    Multiply(GenericExpression left, GenericExpression right) {
        super(left, right, "*");
    }

    @Override
    public int evaluate(int x) {
        if (isConst() && isPreEval()) {
            return getIntConstVal();
        }
        PreEvaluated();
        return super.left.evaluate(x) * super.right.evaluate(x);
    }


    @Override
    public double evaluate(double x) {
        if (isConst() && isPreEval()) {
            return getDoubleConstVal();
        }
        PreEvaluated();
        return super.left.evaluate(x) * super.right.evaluate(x);
    }


    @Override
    public int evaluate(int x, int y, int z) {
        if (isConst() && isPreEval()) {
            return getIntConstVal();
        }
        PreEvaluated();
        return super.left.evaluate(x,y,z) * super.right.evaluate(x,y,z);
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public boolean isCommutative() {
        return true;
    }

}
