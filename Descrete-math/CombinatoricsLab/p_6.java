import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class p_6 {
    static int[] a;
    static FileWriter fw;

    static {
        try {
            fw = new FileWriter("vectors.out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static ArrayList<String> ans = new ArrayList<>();
    static void gen(int p, int n) {
        if (p == n) {
            StringBuilder out = new StringBuilder();
            boolean all1 = true;
            boolean is_writing = true;
            for (int i = 0; i < n; i++){
                if (i<n-1) {
                    if (a[i]==1 && a[i+1]==1) {
                        is_writing = false;
                        break;
                    }
                }
                out.append(a[i]);
                if (a[i]!=1) {
                    all1 = false;
                }
            }
            if (is_writing) {
                ans.add(out.toString());
            }
        } else {
            for (int c = 0; c <= 1; c++) {
                a[p] = c;
                gen(p + 1, n);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("vectors.in"));
        int n = in.nextInt();
        a = new int[n];
        gen(0,n);
        fw.write(String.valueOf(ans.size())+"\n");
        for (int i = 0; i < ans.size(); i++) {
            fw.write(ans.get(i));
            if (i!=ans.size()-1){
                fw.write("\n");
            }
        }
        fw.close();
    }
}
