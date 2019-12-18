package mnk;

public class Game {
    private final Player firstPlayer;
    private final Player secondPlayer;

    public Game(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public int start(Board board) {
        Log log = new Log();
        do {
            final int result1 = move(board, firstPlayer, 1);
            log.log(board.toString());
            if (result1 != -1) {
                return result1;
            }
            final int result2 = move(board, secondPlayer, 2);
            log.log(board.toString());
            if (result2 != -1) {
                return result2;
            }
        } while(true);
    }

    private int move(Board board, Player player, int number) {
        final Move move = player.move(board.getPosition(), board.getTurn());
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