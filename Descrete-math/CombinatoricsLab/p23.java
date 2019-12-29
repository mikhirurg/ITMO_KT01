import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class p23 {
    static int[] a;
    static FileWriter fw;

    static {
        try {
            fw = new FileWriter("nextvector.out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void nextVector() throws IOException {
        int[] tmp = Arrays.copyOf(a, a.length);
        int i = a.length-1;
        int ost = 1;
        while (i >= 0 && ost==1) {
            int old = tmp[i];
            tmp[i] = (old + ost) % 2;
            ost = (old+ost) / 2;
            i--;
        }
        StringBuilder ans = new StringBuilder();
        if (ost == 1){
            ans.append("-");
        } else {
            for (int j = 0; j < a.length; j++) {
                ans.append(tmp[j]);
            }
        }
        fw.write(ans.toString());
    }
    static void prevVector() throws IOException {
        int[] tmp = Arrays.copyOf(a, a.length);
        int i = a.length-1;
        int ost = 1;
        while (i >=0 && ost==1) {
            if (tmp[i] == 0) {
                tmp[i] = 1;
            } else {
                tmp[i] = 0;
                ost = 0;
            }
            i--;
        }
        StringBuilder ans = new StringBuilder();
        if (ost != 0){
            ans.append("-");
        } else {
            for (int j = 0; j < a.length; j++) {
                ans.append(tmp[j]);
            }
        }
        ans.append("\n");
        fw.write(ans.toString());
    }
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("nextvector.in"));
        String vector = in.next();
        a = new int[vector.length()];
        for (int i = 0; i < vector.length(); i++) {
            a[i] = Integer.parseInt(vector.charAt(i)+"");
        }
        prevVector();
        nextVector();
        fw.close();
    }
}
