package expression;

abstract class SingleOperation extends AbstractOperation {
    @Override
    public double evaluate(double x) {
        return 0;
    }

    @Override
    public boolean equals(ToMiniString exp) {
        return false;
    }

    @Override
    public int evaluate(int x) {
        return 0;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return 0;
    }
}
