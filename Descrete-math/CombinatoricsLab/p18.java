import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class p18 {
    static long[][] dp;
    static FileWriter fw;
    static {
        try {
            fw = new FileWriter(new File("brackets2num.out"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void dikWayInit(int n) {
        dp = new long[2 * n + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 0; i < 2 * n; i++) {
            for (int j = 0; j <= n; j++) {
                if (j + 1 <= n) {
                    dp[i + 1][j + 1] += dp[i][j];
                }
                if (j > 0) {
                    dp[i + 1][j - 1] += dp[i][j];
                }
            }
        }
    }
    static void getNum(String seq) throws IOException {
        long answ = 0;
        int d = 0;
        int i = 0;
        while (i < seq.length()) {
            if (seq.charAt(i) == '(') {
                d++;
            } else {
                if (d>=0)
                answ += dp[seq.length()-1-i][d+1];
                d--;
            }
            i++;
        }
        fw.write(String.valueOf(answ));
    }
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("brackets2num.in"));
        String seq = in.next();
        dikWayInit(seq.length()/2+1);
        getNum(seq);
        fw.close();
    }
}
