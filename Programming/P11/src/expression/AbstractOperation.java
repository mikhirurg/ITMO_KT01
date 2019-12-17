package expression;

abstract class AbstractOperation implements Expression, DoubleExpression, TripleExpression {
    public abstract int getPriority();
    public boolean equals(Expression e) {
        return true;
    }
    @Override
    public int hashCode() {
        return 1;
    }
}
