package mnk;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.StringTokenizer;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(PrintStream out, Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this.out = System.out;
        this.in = new Scanner(System.in);
    }

    @Override
    public Move move(final Position pos, final Cell cell) {
        out.println("Current position:");
        out.print(pos);
        out.println("Now is the " + cell + "'s turn.");
        do {
            out.println("Please, enter row and column separated by a whitespace:");
            StringTokenizer input = new StringTokenizer(in.nextLine());
            if(input.countTokens() != 2) {
                out.println("Wrong number of arguments! Expected 2: row and column.");
                continue;
            }

            int row = -1;
            int column = -1;
            try{
                row = Integer.parseInt(input.nextToken());
                column = Integer.parseInt(input.nextToken());
                if (row <= 0 || column <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                out.println("Wrong input format. Params must be > 0");
                continue;
            }
            row--;
            column--;

            Move move = new Move(row, column, cell);
            if (pos.isValid(move)) {
                return move;
            } else {
                out.print("Move {" + (row + 1) + ", " + (column + 1) + "} is invalid.");
            }
        } while(true);
    }
}
