import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class p21 {
    static FileWriter fw;
    static int n;
    static int k;
    static int[][] P;
    static {
        try {
            fw = new FileWriter("num2part.out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void num2part(int num, int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {

            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("num2part.in"));
        int k = in.nextInt();
        int n = k;
        int num = in.nextInt();
        P = new int[n+1][k+1];
        P[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            P[i][0] = 0;
        }
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (j <= i && j > 0) {
                    P[i][j] = P[i][j-1] + P[i-j][j];
                } else {
                    if (j>i) {
                        P[i][j] = P[i][i];
                    }
                }
            }
        }
        System.out.println(P[n][n]);

    }
}
