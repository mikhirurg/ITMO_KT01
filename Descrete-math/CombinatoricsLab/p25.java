import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class p25 {
    static FileWriter fw;

    static {
        try {
            fw = new FileWriter("nextchoose.out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static int[] a;

    static void gen(int n, int k) throws IOException {
        int[] tmp = new int[k+1];
        for (int i = 0; i < k; i++) {
            tmp[i] = a[i];
        }
        tmp[k] = n + 1;
        int i = k - 1;
        while (i >= 0 && tmp[i + 1] - tmp[i] < 2) {
            i--;
        }
        if (i >= 0) {
            tmp[i]++;
            for (int j = i+1; j < k; j++) {
                tmp[j] = tmp[j-1] + 1;
            }
            for (i = 0; i < k; i++) {
                a[i] = tmp[i];
            }
            StringBuilder ans = new StringBuilder();
            for (int j = 0; j < k; j++) {
                ans.append(a[j]);
                if (j!=k-1) {
                    ans.append(" ");
                }
            }
            fw.write(ans.toString());
        } else {
            fw.write("-1");
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("nextchoose.in"));
        int n = in.nextInt();
        int k = in.nextInt();
        a = new int[k+1];
        for (int i = 0; i < k; i++) {
            a[i] = in.nextInt();
        }
        gen(n,k);
        fw.close();
    }
}
