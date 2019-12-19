package mnk;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random;

    public RandomPlayer(Random random) {
        this.random = random;
    }

    public RandomPlayer() {
        random = new Random();
    }

    @Override
    public Move move(final Position pos, final Cell cell) {
        Move move;
        do {
            int row = random.nextInt(pos.getRowNumber());
            int column = random.nextInt(pos.getColumnNumber());
            move = new Move(row, column, cell);
        } while(!pos.isValid(move));
        return move;
    }
}
