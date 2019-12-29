import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class p15 {
    static FileWriter fw;

    static {
        try {
            fw = new FileWriter("num2choose.out");
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

    static void gen(int n, int k, BigInteger m, BigInteger[][] C) throws IOException {
        int next = 1;
        StringBuilder ans = new StringBuilder();
        while (k > 0) {
            if (new BigInteger(String.valueOf(m)).compareTo(C[n-1][k-1]) < 0) {
                ans.append(next);
                ans.append(" ");
                k--;
            } else {
                m = m.subtract(C[n-1][k-1]);
            }
            n--;
            next++;
        }
        ans.setLength(ans.length()-1);
        fw.write(String.valueOf(ans));
    }
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("num2choose.in"));
        int n = in.nextInt();
        int k = in.nextInt();
        BigInteger m = new BigInteger(in.next());
        BigInteger[][] C = new BigInteger[n+1][k+1];
        for (int i = 0; i <= n; i++) {
            C[i][0] = new BigInteger("1");
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(k, i); j++) {
                C[i][j] = fact(new BigInteger(String.valueOf(i))).divide(fact(new BigInteger(String.valueOf(j))).multiply(fact(new BigInteger(String.valueOf(i - j)))));
            }
        }
        gen(n, k, m, C);
        fw.close();
    }
}
