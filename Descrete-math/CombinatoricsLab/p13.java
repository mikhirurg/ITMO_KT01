import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class p13 {
    static int[] a;
    static boolean[] used;

    static FileWriter fw;

    static {
        try {
            fw = new FileWriter("num2perm.out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static BigInteger fact(BigInteger n) {
        if (n.equals(new BigInteger("0")) || n.equals(new BigInteger("1"))){
            return new BigInteger("1");
        }
        return n.multiply(fact(n.subtract(new BigInteger("1"))));
    }

    static void gen(int p, int n, BigInteger k) throws IOException {
        if (p==n){
            assert (k.equals(new BigInteger("0")));
            StringBuilder out = new StringBuilder();
            for (int i = 0; i<n; i++){
                out.append(a[i]);
                if (i!=n-1) {
                    out.append(" ");
                }
            }
            fw.write(out.toString());
        } else {
            for (int c = 1; c <= n; c++) {
                if (!used[c-1]) {
                    a[p] = c;
                    BigInteger cnt = fact(new BigInteger(String.valueOf(n - p - 1)));
                    if (cnt.compareTo(k)<=0) {
                        k = k.subtract(cnt);
                    } else {
                        used[c-1] = true;
                        gen(p+1, n, k);
                        return;
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("num2perm.in"));
        int n = in.nextInt();
        BigInteger k = new BigInteger(in.next());
        a = new int[n];
        used = new boolean[n];
        gen(0,n,k);
        fw.close();
    }
}
