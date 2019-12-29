import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class p05 {
    static int[] a;
    static FileWriter fw;

    static {
        try {
            fw = new FileWriter("telemetry.out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String getVector(int []a) {
        StringBuilder sb = new StringBuilder();
        for (int i : a) {
            sb.append(i);
        }
        return sb.toString();
    }

    static void genTel(int n, int base) throws IOException {
        int len = (int) Math.pow(base,n);
        Set<String> vectors = new HashSet<>();
        int count = 0;
        int prev = -1;
        while (count < len) {
            int[] copy = Arrays.copyOf(a, a.length);
            for (int i = 0; i < n; i++) {
                if (copy[i] > 0) {
                    if (i!=prev) {
                        prev = i;
                        copy[i]--;
                        String ans = getVector(copy);
                        if (!vectors.contains(ans)) {
                            vectors.add(ans);
                            fw.write(ans);
                            System.out.println(ans);
                            count++;
                            if (count != len) fw.write("\n");
                            a[i]--;
                            break;
                        }
                    }
                } else {
                    if (copy[i] < base - 1) {
                        if (i != prev) {
                            prev = i;
                            copy[i]++;
                            String ans = getVector(copy);
                            if (!vectors.contains(ans)) {
                                vectors.add(ans);
                                fw.write(ans);
                                System.out.println(ans);
                                count++;
                                if (count != len) fw.write("\n");
                                a[i]++;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("telemetry.in"));
        int n = in.nextInt();
        int k = in.nextInt();
        a = new int[n];
        StringBuilder first = new StringBuilder();
        for (int i = 0; i < n; i++) {
            first.append("0");
        }
        genTel(n, k);
        fw.close();
    }
}
