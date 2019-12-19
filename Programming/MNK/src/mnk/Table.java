package mnk;

import java.util.ArrayList;
import java.util.Arrays;

public class Table {
    private int pointerFirst;
    private int pointerSecond;
    int[] currentScore = new int[2];
    ArrayList<int[]> history = new ArrayList<>();
    Table() {
        pointerFirst = 0;
        pointerSecond = 1;
    }

    public void addFirst(int score) {
        currentScore[pointerFirst] += score;
        int[] tmpScore = Arrays.copyOf(currentScore, currentScore.length);
        history.add(tmpScore);
    }

    public void addSecond(int score) {
        currentScore[pointerSecond] += score;
        int[] tmpScore = Arrays.copyOf(currentScore, currentScore.length);
        history.add(tmpScore);
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
        sb.append("Match review: \n");
        sb.append("First player \t:\t Second Player\n");
        int pFirst = 0;
        int pSecond = 1;
        for (int[] hist : history) {
            sb.append("\t   ");
            sb.append(hist[pFirst]);
            sb.append("     \t          ");
            sb.append(hist[pSecond]);
            sb.append("\n");
            int tmp = pFirst;
            pFirst = pSecond;
            pSecond = tmp;
        }
        return sb.toString();
    }
}
