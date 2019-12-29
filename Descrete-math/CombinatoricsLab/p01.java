import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class p01 {
    static int[] a;
    static FileWriter fw;

    static {
        try {
            fw = new FileWriter("allvectors.out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void gen(int p, int n) throws IOException {
        if (p == n) {
            StringBuilder out = new StringBuilder();
            boolean all1 = true;
            for (int i = 0; i < n; i++){
                out.append(a[i]);
                if (a[i]!=1) {
                    all1 = false;
                }
            }
            out.append(all1 ? "" : "\n");
            fw.write(out.toString());
        } else {
            for (int c = 0; c <= 1; c++) {
                a[p] = c;
                gen(p + 1, n);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("allvectors.in"));
        int n = in.nextInt();
        a = new int[n];
        gen(0,n);
        fw.close();
    }
}
