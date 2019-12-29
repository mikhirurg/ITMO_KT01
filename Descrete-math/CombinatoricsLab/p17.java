import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class p17 {
    static long[][] dp;
    static FileWriter fw;

    static {
        try {
            fw = new FileWriter(new File("num2brackets.out"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void next(int n, long k) throws IOException {
        StringBuilder answ = new StringBuilder();
        int d = 0;
        int i = n * 2 - 1;
        while (i >= 0) {
            if (d+1<=n && d>=0 && dp[i][d + 1] >= k) {
                answ.append("(");
                d++;
            } else {
                if (d + 1 <= n && d>=0) {
                    k = k - dp[i][d + 1];
                }
                answ.append(")");
                d--;
            }
            i--;
        }
        fw.write(answ.toString());
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

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("num2brackets.in"));
        int n = in.nextInt();
        long k = in.nextLong()+1;
        dikWayInit(n);
        next(n, k);
        fw.close();
    }
}
