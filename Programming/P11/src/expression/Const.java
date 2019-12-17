package expression;

public class Const implements Expression, DoubleExpression, TripleExpression {
    private Object value;

    public Const(int value) {
        this.value = value;
    }

    public Const(double value) {
        this.value = value;
    }

    @Override
    public int evaluate(int x) {
        return (int) value;
    }

    @Override
    public double evaluate(double x) {
        return (double) value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return 0;
    }

    @Override
    public String toMiniString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(ClassExpression exp) {
        return this.toString().equals(exp.toString());
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
