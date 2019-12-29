import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class p11 {
    static int[] a;
    static FileWriter fw;

    static {
        try {
            fw = new FileWriter("subsets.out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static int count = 0;
    static void genBit(int n, int pos, String answ) throws IOException {
        if (pos == 0 || pos > n) {
            String ans = answ;
            if (ans.length() > 0) {
                ans = ans.substring(0, ans.length() - 1);
            }
            fw.write(ans);
            count++;
            if (count!=1<<n) {
                fw.write("\n");
            }
        } else {
            genBit(n, 0, answ);
            for (int i = pos; i <= n; i++) {
                genBit(n, i + 1, answ+i+" ");
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("subsets.in"));
        int n = in.nextInt();
        a = new int[n];
        genBit(n, 1, "");
        fw.close();
    }
}
