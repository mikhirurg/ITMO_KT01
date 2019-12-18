package mnk;

public interface Position {
    int getRowNumber();
    int getColumnNumber();
    boolean isValid(Move move);
    Cell getCell(int row, int column);
}
