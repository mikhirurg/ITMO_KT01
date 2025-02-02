package mnk;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int row = -1;
        int column = -1;
        int win = -1;

        while (true) {
            System.out.println("Enter number of rows, columns and winning number separated by a whitespace:");
            String[] input = sc.nextLine().split("\\s+");
            if (input.length != 3) {
                System.out.println("Wrong number of arguments.");
                continue;
            }

            try{
                row = Integer.parseInt(input[0]);
                column = Integer.parseInt(input[1]);
                win = Integer.parseInt(input[2]);
                if (row <= 0 || column <= 0 || win <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong input format. Params must be > 0");
                continue;
            }
            break;
        }

        Player playerOne = null;
        System.out.println("Who will be the first player?");
        while (playerOne == null) {
            System.out.println("1 - RandomPlayer, 2 - HumanPlayer");
            String input = sc.next();
            if (input.equals("1")) {
                playerOne = new RandomPlayer();
            } else if (input.equals("2")) {
                playerOne = new HumanPlayer();
            } else {
                System.out.println("No such player!");
            }
        }

        Player playerTwo = null;
        System.out.println("Choose second player: ");
        while (playerTwo == null) {
            System.out.println("1 - RandomPlayer, 2 - Human");
            String input = sc.next();
            if (input.equals("1")) {
                playerTwo = new RandomPlayer();
            } else if (input.equals("2")) {
                playerTwo = new HumanPlayer();
            } else {
                System.out.println("No such player!");
            }
        }

        final Game game = new Game(playerOne, playerTwo);

        int result = game.start(new MNKBoard(row, column, win));
        if (result != 0) {
            System.out.println("Player " + result + " won!");
        } else {
            System.out.println("Draw!");
        }


        sc.close();
    }
}