import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class p24 {
    static FileWriter fw;

    static {
        try {
            fw = new FileWriter("nextperm.out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int []a;

    static void nextPerm(int n) throws IOException {
        int[] tmp = Arrays.copyOf(a, n);
        for (int i = n - 2; i>=0; i--) {
            if (tmp[i] < tmp[i+1]) {
                int min = i+1;
                for (int j = i+1; j < n; j++) {
                    if (tmp[j] < tmp[min] && tmp[j]>tmp[i]) {
                        min = j;
                    }
                }
                int t = tmp[i];
                tmp[i] = tmp[min];
                tmp[min] = t;
                for (int k = i + 1; k < (i+1+n)/2; k++) {
                    t = tmp[k];
                    tmp[k] = tmp[n-(k-i-1)-1];
                    tmp[n-(k-i-1)-1] = t;
                }
                StringBuilder ans = new StringBuilder();
                for (int k = 0; k < n; k++) {
                    ans.append(tmp[k]);
                    if (k!=n-1){
                        ans.append(" ");
                    }
                }
                fw.write(ans.toString());
                return;
            }
        }
        StringBuilder ans = new StringBuilder();
        ans.append("0 ".repeat(Math.max(0, n)));
        ans.setLength(ans.length()-1);
        fw.write(ans.toString());
    }

    static void prevPerm(int n) throws IOException {
        int[] tmp = Arrays.copyOf(a, n);
        for (int i = n - 2; i>=0; i--) {
            if (tmp[i] > tmp[i+1]) {
                int max = i+1;
                for (int j = i+1; j < n; j++) {
                    if (tmp[j] > tmp[max] && tmp[j]<tmp[i]) {
                        max = j;
                    }
                }
                int t = tmp[i];
                tmp[i] = tmp[max];
                tmp[max] = t;
                for (int k = i + 1; k < (i+1+n)/2; k++) {
                    t = tmp[k];
                    tmp[k] = tmp[n-(k-i-1)-1];
                    tmp[n-(k-i-1)-1] = t;
                }
                StringBuilder ans = new StringBuilder();
                for (int k = 0; k < n; k++) {
                    ans.append(tmp[k]);
                    if (k!=n-1){
                        ans.append(" ");
                    }
                }
                ans.append("\n");
                fw.write(ans.toString());
                return;
            }
        }
        StringBuilder ans = new StringBuilder();
        ans.append("0 ".repeat(Math.max(0, n)));
        ans.setLength(ans.length()-1);
        ans.append("\n");
        fw.write(ans.toString());
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("nextperm.in"));
        int n = in.nextInt();
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        prevPerm(n);
        nextPerm(n);
        fw.close();
    }
}
