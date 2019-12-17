package expression;

public interface ClassExpression extends Expression, TripleExpression, DoubleExpression {
    String toString();
    String toMiniString();
    boolean equals(ClassExpression exp);
}