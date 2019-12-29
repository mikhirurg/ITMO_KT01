import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class p_07 {
    static boolean[] used;
    static int[] a;
    static FileWriter fw;
    static int count = 0;

    static int fact(int n) {
        if (n == 0 || n == 1){
            return 1;
        }
        return n*fact(n-1);
    }

    static {
        try {
            fw = new FileWriter("permutations.out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void gen(int pos, int n, int len) throws IOException {
        if (pos == n) {

            count++;
            StringBuilder out = new StringBuilder();
            for (int i = 0; i < n; i++) {
                out.append(a[i]);
                if (i != n-1) {
                    out.append(" ");
                }
            }
            fw.write(out.toString());
            if (count != len) {
                fw.write("\n");
            }
        } else {
            for (int c = 1; c <= n; c++) {
                if (!used[c-1]) {
                    a[pos] = c;
                    used[c-1] = true;
                    gen(pos+1, n, len);
                    used[c-1] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("permutations.in"));
        int n = in.nextInt();
        a = new int[n];
        used = new boolean[n];
        int len = fact(n);
        gen(0, n, len);
        fw.close();
    }
}
