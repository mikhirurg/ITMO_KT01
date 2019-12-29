import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class p_08 {
    static FileWriter fw;
    static int[] a;
    static {
        try {
            fw = new FileWriter("choose.out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static long fact(long n) {
        if (n == 0 || n == 1){
            return 1;
        }
        return n*fact(n-1);
    }

    static int count = 0;

    static void gen(int p, int n, int k, long len) throws IOException {
        if (p == k) {
            StringBuilder out = new StringBuilder();
            for (int i = 0; i < k; i++) {
                out.append(a[i]);
                if (i != k-1) {
                    out.append(" ");
                }
            }
            count++;
            if (count != len) {
                out.append("\n");
            }
            fw.write(out.toString());
        } else {
            for (int c = 1; c <= n; c++) {
                    if (((p == 0) || (c > a[p - 1])) && (n - c) >= k - (p + 1)) {
                        a[p] = c;
                        gen(p + 1, n, k, len);
                    }
                }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("choose.in"));
        int n = in.nextInt();
        int k = in.nextInt();
        a = new int[k];
        long len = fact(n) / (fact(k) * fact(n-k));
        gen(0,n,k,len);
        fw.close();
    }
}
