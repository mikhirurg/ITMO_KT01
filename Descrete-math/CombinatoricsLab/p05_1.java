import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class p05_1 {
    static int[] a;
    static int count = 0;
    static FileWriter fw;
    static {
        try {
            fw = new FileWriter("telemetry.out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int digit(int n, int base, int i) {
        return (int) (Math.floor(n / (Math.pow(base, i))) % base);
    }

    static void gen(int base, int n) throws IOException {
        int len = (int) Math.pow(base, n);
        int[][] matrix = new int[len][n];
        for (int i = n-1; i>=0; i--) {
            int pos = 0;
            int bas = (int) (len / (Math.pow(base,i+1)));
            for (int r = 0; r < bas; r++) {
                if (r % 2 == 0) {
                    for (int j = 0; j < base; j++) {
                        int k = 0;
                        for (k = pos; k < pos + len / (bas*base); k++) {
                            matrix[k][i] = j;
                        }
                        pos = k;
                    }
                } else {
                    for (int j = base-1; j >= 0; j--) {
                        int k = 0;
                        for (k = pos; k < pos + len / (bas*base); k++) {
                            matrix[k][i] = j;
                        }
                        pos = k;
                    }
                }
            }
        }

        for (int i = 0; i < len; i++) {
            fw.write(getVector(matrix[i]));
            if (i!=len-1) {
                fw.write("\n");
            }
        }

    }

    static String getVector(int []a) {
        StringBuilder sb = new StringBuilder();
        for (int i : a) {
            sb.append(i);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("telemetry.in"));
        int n = in.nextInt();
        int k = in.nextInt();
        a = new int[n];
        gen(k, n);
        fw.close();
    }
}
