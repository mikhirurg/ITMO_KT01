package mnk;

public class Game {
    private final Player firstPlayer;
    private final Player secondPlayer;

    public Game(Player playerFirst, Player playerSecond) {
        this.firstPlayer = playerFirst;
        this.secondPlayer = playerSecond;
    }

    public int start(Board board) {
        Board fake = board.copy();
        Log log = new Log();
        do {
            final int result1 = move(fake, firstPlayer, 1);
            log.log(board.toString());
            if (result1 != -1) {
                return result1;
            }
            final int result2 = move(fake, secondPlayer, 2);
            log.log(board.toString());
            if (result2 != -1) {
                return result2;
            }
        } while(true);
    }

    private int move(Board board, Player player, int number) {
        Board fake = board.copy();
        final Move move = player.move(fake.getPosition(), fake.getTurn());
        Result result = board.makeMove(move);
        switch (result) {
            case WIN: return number;
            case DRAW: return 0;
            case CONTINUE: return -1;
            case LOSE: return 3 - number;
        }
        throw new IllegalStateException("Got an unexpected result\n");
    }
}