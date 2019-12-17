package expression;

public class Variable implements Expression, DoubleExpression, TripleExpression {

    String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public double evaluate(double x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return 0;
    }

    @Override
    public String toMiniString() {
        return variable;
    }

    @Override
    public boolean equals(ClassExpression exp) {
        return false;
    }


    @Override
    public String toString() {
        return variable;
    }

}
