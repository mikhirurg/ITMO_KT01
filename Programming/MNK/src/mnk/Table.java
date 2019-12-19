package mnk;

import java.util.ArrayList;

public class Table {
    private int rounds;
    private int pointerFirst;
    private int pointerSecond;
    int[] currentScore = new int[2];
    ArrayList<int[]> history = new ArrayList<>();
    Table(int rounds) {
        this.rounds = rounds;
        pointerFirst = 0;
        pointerSecond = 1;
    }

    public void addFirst(int score) {
        currentScore[pointerFirst] += score;
        history.add(currentScore);
    }

    public void addSecond(int score) {
        currentScore[pointerSecond] += score;
        history.add(currentScore);
    }

    public int totalScore() {
        return currentScore[0] + currentScore[1];
    }

    public int getFirstScore() {
        return currentScore[pointerFirst];
    }

    public int getSecondScore() {
        return currentScore[pointerSecond];
    }


    public String getResult() {
        if (getFirstScore() == getSecondScore()) {
            return "draw";
        } else {
            return getFirstScore() > getSecondScore() ? "First player" : "Second player";
        }
    }

    public void swapPlayers() {
        int tmp = pointerFirst;
        pointerFirst = pointerSecond;
        pointerSecond = tmp;
    }

    public String matchReview() {
        StringBuilder sb = new StringBuilder();
        sb.append("First player \t:\t Second Player\n");
        for (int[] hist : history) {
            sb.append("\t   ");
            sb.append(hist[pointerFirst]);
            sb.append("     \t        ");
            sb.append(hist[pointerSecond]);
            sb.append("\n");
        }
        return sb.toString();
    }
}
