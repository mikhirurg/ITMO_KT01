package expression;

abstract class BinaryOperation extends AbstractOperation {
    ToMiniString first;
    ToMiniString second;

    BinaryOperation(ToMiniString first, ToMiniString second) {
        this.first = first;
        this.second = second;
    }

    public boolean equals(ClassExpression exp) {
        return this.toString().equals(exp.toString());
    }
}
