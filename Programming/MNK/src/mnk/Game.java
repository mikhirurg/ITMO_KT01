package mnk;

public class Game {
    private final Player firstPlayer;
    private final Player secondPlayer;

    public Game(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public int startMatch(Board board, int number) {
        Log log = new Log(System.out);
        log.log("Get ready for the Match!");
        Table matchTable = new Table(number);
        while (matchTable.totalScore() != number) {
            final int result = this.startSingleRound(board);
            switch (result) {
                case 1:
                    matchTable.addFirst(1);
                    break;
                case 2:
                    matchTable.addSecond(1);
                    continue;
            }
            matchTable.swapPlayers();
        }
        if (matchTable.getFirstScore() == matchTable.getSecondScore()) {
            log.log(matchTable.getResult());
            log.log(matchTable.matchReview());
            return 0;
        }
        log.log(matchTable.getResult()+" wins!");
        log.log(matchTable.matchReview());
        return (matchTable.getFirstScore() > matchTable.getSecondScore() ? 1 : 2);
    }

    public int startSingleRound(Board board) {
        Log log = new Log();
        while(true) {
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
        }
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