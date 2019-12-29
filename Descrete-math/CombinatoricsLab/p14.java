import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class p14 {
    static FileWriter fw;

    static {
        try {
            fw = new FileWriter("perm2num.out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static boolean[] used;
    static int[] a;
    static BigInteger[] P;
    static BigInteger fact(BigInteger n) {
        if (n.equals(new BigInteger("0")) || n.equals(new BigInteger("1"))){
            return new BigInteger("1");
        }
        return n.multiply(fact(n.subtract(new BigInteger("1"))));
    }
    static BigInteger gen(int n) {
        BigInteger num = new BigInteger("0");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < a[i]; j++) {
                if (!used[j]) {
                    num = num.add(P[n-i]);
                }
            }
            used[a[i]] = true;
        }
        return num;
    }
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("perm2num.in"));
        int n = in.nextInt();
        a = new int[n+1];
        P = new BigInteger[n+1];
        used = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            P[i] = fact(new BigInteger(String.valueOf(i)));
        }
        fw.write(String.valueOf(gen(n)));
        fw.close();
    }
}
