package mnk;

public class ProtectedPosition implements Position {
    private Position position;

    ProtectedPosition(Position board) {
        this.position = board;
    }

    @Override
    public int getRowNumber() {
        return position.getRowNumber();
    }

    @Override
    public int getColumnNumber() {
        return position.getColumnNumber();
    }

    @Override
    public boolean isValid(Move move) {
        return position.isValid(move);
    }

    @Override
    public Cell getCell(int row, int column) {
        return position.getCell(row, column);
    }

    @Override
    public String toString() {
        return position.toString();
    }
}
