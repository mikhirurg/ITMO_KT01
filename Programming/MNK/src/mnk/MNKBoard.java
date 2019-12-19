package mnk;

import java.util.Arrays;
import java.util.Map;

public final class MNKBoard implements Board, Position {
    private static final Map<Cell, Character> DISPLAY = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );
    private final Cell[][] field;
    private int emptyCells;
    private Cell turn;
    private int rowNumber;
    private int columnNumber;
    private int winNumber;

    public MNKBoard(int rowNumber, int columnNumber, int winNumber) {
        if (!(rowNumber > 0 && columnNumber > 0 && winNumber > 0)) {
            throw new NumberFormatException("Wrong arguments!");
        }
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.winNumber = winNumber;
        field = new Cell[rowNumber][columnNumber];
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
        emptyCells = rowNumber * columnNumber;
        turn = Cell.X;
    }

    @Override
    public int getRowNumber() {
        return rowNumber;
    }

    @Override
    public int getColumnNumber() {
        return columnNumber;
    }

    @Override
    public Position getPosition() {
        return new ProtectedPosition(this);
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public Cell getCell(int row, int column) {
        return field[row][column];
    }

    @Override
    public boolean isValid(Move move) {
        return (0 <= move.getRow() && move.getRow() < rowNumber &&
                0 <= move.getColumn() && move.getColumn() < columnNumber &&
                field[move.getRow()][move.getColumn()] == Cell.E &&
                move.getCell() == turn);
    }

    @Override
    public Result makeMove(Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        int row = move.getRow();
        int column = move.getColumn();
        Cell cell = move.getCell();
        field[row][column] = cell;
        emptyCells--;
        int checkMainDiag = checkMove(row - 1, column - 1, -1, -1, cell) +
                        checkMove(row + 1, column + 1, 1, 1, cell) + 1;
        int checkSecondDiag = checkMove(row - 1, column + 1, -1, 1, cell) +
                        checkMove(row + 1, column - 1, 1, -1, cell) + 1;
        int checkRow = checkMove(row, column - 1, 0, -1, cell) +
                        checkMove(row, column + 1, 0, 1, cell) + 1;
        int checkCol = checkMove(row - 1, column, -1, 0, cell) +
                        checkMove(row + 1, column, 1, 0, cell) + 1;

        if (checkMainDiag >= winNumber || checkSecondDiag >= winNumber || checkRow >= winNumber || checkCol >= winNumber) {
            return Result.WIN;
        } else {
            if (emptyCells == 0) {
                return Result.DRAW;
            } else {
                turn = (turn == Cell.X) ? (Cell.O) : (Cell.X);
                return Result.CONTINUE;
            }
        }

    }

    @Override
    public void clearBoard() {
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
        emptyCells = rowNumber * columnNumber;
    }

    private int checkMove(int currRow, int currCol, int rowDelta, int colDelta, Cell cell) {
        if (currRow >= 0 && currRow < rowNumber && currCol >= 0 && currCol < columnNumber && field[currRow][currCol] == cell) {
            return checkMove(currRow + rowDelta, currCol + colDelta, rowDelta, colDelta, cell) + 1;
        }
        else {
            return 0;
        }
    }

    @Override
    public String toString() {
        StringBuilder toOut = new StringBuilder(" \t");
        for (int i = 1; i <= columnNumber; i++) {
            toOut.append(i);
            toOut.append("\t");
        }
        for (int i = 0; i < rowNumber; i++) {
            toOut.append("\n");
            toOut.append((i + 1));
            toOut.append("\t");
            for (int j = 0; j < columnNumber; j++) {
                toOut.append(DISPLAY.get(field[i][j]));
                toOut.append("\t");
            }
        }
        toOut.append('\n');
        return toOut.toString();
    }
}
