package mnk;

public interface Board {
    Position getPosition();
    Cell getTurn();
    Result makeMove(Move move);
}
