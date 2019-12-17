package expression;

public class Multiply extends BinaryOperation implements Expression, DoubleExpression, TripleExpression {

    public Multiply(ClassExpression first, ClassExpression second) {
        super(first, second);
    }

    @Override
    public String toMiniString() {
        return null;
    }


    @Override
    public String toString() {
        return "("+super.first.toString()+" * "+super.second.toString()+")";
    }

    @Override
    public int evaluate(int x) {
        return super.first.evaluate(x) * super.second.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return 0;
    }

    @Override
    public double evaluate(double x) {
        return super.first.evaluate(x) * super.second.evaluate(x);
    }

    @Override
    public boolean equals(ClassExpression exp) {
        return this.toString().equals(exp.toString());
    }

}
