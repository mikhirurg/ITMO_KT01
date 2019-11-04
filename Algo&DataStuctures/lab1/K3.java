import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class K3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[][] a = new int[n][3];
        int[][] b = new int[n][3];
        double M = 0;
        for (int i = 0; i < n; i++) {
            a[i][0] = in.nextInt();
            a[i][1] = in.nextInt();
            a[i][2] = i;
        }
        M = 10000000.0;
        double l = 0;
        double r = M;
        int[] ans = new int[k];
        for (int j = 0; j < 200; j++) {
            double mid = (l + r) / 2;
            double sumV = 0;
            double sumW = 0;
            for (int i = 0; i < n; i++) {
                b[i][0] = a[i][0];
                b[i][1] = a[i][1];
                b[i][2] = a[i][2];
            }
            Arrays.sort(b, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[0]-o1[1]*mid == o2[0]-o2[1]*mid) return 1;
                    if (o1[0] - o1[1] * mid > o2[0] - o2[1] * mid) {
                        return 1;
                    } else return -1;
                }
            });
            for (int i = n - k; i < n; i++) {
                sumV += b[i][0];
                sumW += b[i][1];
                ans[i-n+k] = (b[i][2]);
            }
            if (sumV > mid * sumW) {
                l = mid;
            } else r = mid;
        }
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i] + 1);
        }
    }
}
