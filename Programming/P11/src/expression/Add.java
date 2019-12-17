package expression;

public class Add extends BinaryOperation {

    public Add(ToMiniString first, ToMiniString second) {
        super(first, second);
    }

    @Override
    public int evaluate(int x) {
        return super.first.evaluate(x) + super.second.evaluate(x);
    }

    @Override
    public String toMiniString() {
        return null;
    }

    @Override
    public boolean equals(ToMiniString exp) {
        return this.toString().equals(exp.toString());
    }

    @Override
    public String toString() {
        return "("+super.first.toString()+" + "+super.second.toString()+")";
    }

    @Override
    public boolean equals(ClassExpression exp) {
        return this.toString().equals(exp.toString());
    }

    @Override
    public double evaluate(double x) {
        return super.first.evaluate(x) + super.second.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return 0;
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
