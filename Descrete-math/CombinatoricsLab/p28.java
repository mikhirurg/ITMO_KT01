import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class p28 {
    static FileWriter fw;

    static {
        try {
            fw = new FileWriter("nextmultiperm.out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void gen(int n, int[] source) throws IOException {
        int i,j;
        for (i = n-2; i>=0 && source[i] >= source[i+1]; i--);
        if (i >= 0) {
            for (j = i + 1; j < n - 1 && source[j+1] > source[i]; j++);
            int tmp = source[i];
            source[i] = source[j];
            source[j] = tmp;
            for (int k = i + 1; k < (i+1+n)/2; k++) {
                tmp = source[k];
                source[k] = source[n-(k-i-1)-1];
                source[n-(k-i-1)-1] = tmp;
            }
            for (int k = 0; k < n; k++) {
                fw.write(String.valueOf(source[k]));
                if (k != n - 1) {
                    fw.write(" ");
                }
            }
        } else {
            for (int k = 0; k < n; k++) {
                fw.write("0");
                if (k != n - 1) {
                    fw.write(" ");
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("nextmultiperm.in"));
        int n = in.nextInt();
        int[] source = new int[n];
        for (int i = 0; i < n; i++) {
            source[i] = in.nextInt();
        }
        gen(n,source);
        fw.close();
    }
}
