import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class p09 {
    static FileWriter fw;
    static char[] a;
    static {
        try {
            fw = new FileWriter("brackets.out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static List<String> brackets = new ArrayList<>();
    static void gen(int pos, int n, int bal) {
        if (pos == 2*n) {
            StringBuilder ans = new StringBuilder();
            for (int i = 0; i < 2*n; i++) {
                ans.append(a[i]);
            }
            brackets.add(ans.toString());
        } else {
            if (2*n - pos - 1 >= bal + 1) {
                a[pos] = '(';
                gen(pos+1, n, bal+1);
            }
            if (bal > 0) {
                a[pos] = ')';
                gen(pos + 1, n,bal-1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("brackets.in"));
        int n = in.nextInt();
        a = new char[2*n];
        gen(0, n, 0);
        for (int i = 0; i < brackets.size(); i++) {
            fw.write(brackets.get(i));
            if (i != brackets.size()-1) {
                fw.write("\n");
            }
        }
        fw.close();
    }
}
