package mnk;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    private static Player getPlayer(Scanner in) {
        Player player = null;
        while (player == null) {
            System.out.println("1 - RandomPlayer, 2 - HumanPlayer");
            StringTokenizer input = new StringTokenizer(in.nextLine());
            if (input.countTokens() != 1) {
                System.out.println("Wrong number of arguments.");
                continue;
            }
            String number = input.nextToken();
            if (number.equals("1")) {
                player = new RandomPlayer();
            } else {
                if (number.equals("2")) {
                    player = new HumanPlayer();
                } else {
                    System.out.println("No such player!");
                }
            }
        }
        return player;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int row;
        int column;
        int win;

        while (true) {
            System.out.println("Enter number of rows, columns and winning number separated by a whitespace:");
            StringTokenizer input = new StringTokenizer(in.nextLine());
            if (input.countTokens() != 3) {
                System.out.println("Wrong number of arguments.");
                continue;
            }
            try{
                row = Integer.parseInt(input.nextToken());
                column = Integer.parseInt(input.nextToken());
                win = Integer.parseInt(input.nextToken());
                if (row <= 0 || column <= 0 || win <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong input format. Params must be int value > 0");
                continue;
            }
            break;
        }

        Player playerOne;
        System.out.println("Who will be the first player?");
        playerOne = getPlayer(in);

        Player playerTwo;
        System.out.println("Choose second player: ");
        playerTwo = getPlayer(in);

        final Game game = new Game(playerOne, playerTwo);
        int rounds;
        while (true) {
            System.out.println("Enter number of rounds in match:");
            StringTokenizer input = new StringTokenizer(in.nextLine());
            if (input.countTokens() != 1) {
                System.out.println("Wrong number of arguments.");
                continue;
            }
            try{
                rounds = Integer.parseInt(input.nextToken());
                if (rounds <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong input format. Params must be int value > 0");
                continue;
            }
            break;
        }
        int result = game.startMatch(new MNKBoard(row, column, win), rounds);
        if (result != 0) {
            System.out.println("Player " + result + " won!");
        } else {
            System.out.println("Draw!");
        }
        in.close();
    }
}