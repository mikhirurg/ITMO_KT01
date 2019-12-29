import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class p16 {
    static FileWriter fw;

    static {
        try {
            fw = new FileWriter("choose2num.out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static boolean[] used;
    static int[] a;
    static BigInteger[][] C;
    static BigInteger fact(BigInteger n) {
        if (n.equals(new BigInteger("0")) || n.equals(new BigInteger("1"))){
            return new BigInteger("1");
        }
        return n.multiply(fact(n.subtract(new BigInteger("1"))));
    }
    static BigInteger gen(int n, int k) {
        BigInteger num = new BigInteger("0");
        for (int i = 1; i <= k; i++) {
            for (int j = a[i-1]+1; j < a[i]; j++) {
                num = num.add(C[n-j][k-i]);
            }
        }
        return num;
    }
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("choose2num.in"));
        int n = in.nextInt();
        int k = in.nextInt();
        a = new int[k+1];
        C = new BigInteger[n+1][k+1];
        for (int i = 1; i <= k; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            C[i][0] = new BigInteger("1");
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(k, i); j++) {
                C[i][j] = fact(new BigInteger(String.valueOf(i))).divide(fact(new BigInteger(String.valueOf(j))).multiply(fact(new BigInteger(String.valueOf(i - j)))));
            }
        }
        fw.write(String.valueOf(gen(n,k)));
        fw.close();
    }
}
