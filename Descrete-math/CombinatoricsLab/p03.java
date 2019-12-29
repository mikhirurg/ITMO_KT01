import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class p03 {
    static ArrayList<String> vectors = new ArrayList<>();
    static int[] a;
    static void gen(int p, int n) {
        if (p == n) {
            StringBuilder out = new StringBuilder();
            boolean all1 = true;
            for (int i = 0; i < n; i++){
                out.append(a[i]);
                if (a[i]!=1) {
                    all1 = false;
                }
            }
            vectors.add(out.toString());
        } else {
            for (int c = 0; c <= 2; c++) {
                a[p] = c;
                gen(p + 1, n);
            }
        }
    }
    static char nextChar(char in) {
        switch (in){
            case '0': return '1';
            case '1': return '2';
            case '2': return '0';
        }
        return 'E';
    }
    static String shift(String in) {
        StringBuilder out = new StringBuilder();
        for (char c : in.toCharArray()) {
            out.append(nextChar(c));
        }
        return out.toString();
    }
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("antigray.in"));
        int n = in.nextInt();
        a = new int[n];

        gen(0, n);

        int len = (int) Math.pow(3, n-1);
        FileWriter fw = new FileWriter("antigray.out");
        for (int i = 0; i < len; i++) {
            fw.write(vectors.get(i)+"\n");
            String current = vectors.get(i);
            current = shift(current);
            fw.write(current+"\n");
            current = shift(current);
            fw.write(current);
            if (i != len-1) {
                fw.write("\n");
            }
        }

        fw.close();
    }
}
