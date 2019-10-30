import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;

public class A {
    static void next_args(int[] val) {
        int start = val.length - 1;
        val[start]++;
        while (val[start] == 2 && start > 0) {
            if (val[start] > 1) {
                val[start] = 0;
                val[start - 1]++;
                start--;
            }
        }
    }

    static void print(Integer[] val) {
        for (int i = 0; i < val.length; i++) {
            System.out.print(val[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
            Scanner s = new Scanner(System.in);
            int n = s.nextInt();
            int m = s.nextInt();
            HashSet<String> korn = new HashSet<>();
            for (int i = 0; i < m; i++) {
                int[] tmp = new int[2];
                tmp[0] = s.nextInt();
                tmp[1] = s.nextInt();
                Arrays.sort(tmp);
                String tms = tmp[0] + " " + tmp[1];
                korn.add(tms);
            }
            boolean sum = false;
            int[] a = new int[n];
            for (int i = 0; i < Math.pow(2, n); i++) {
                boolean mult = true;
                for (String p : korn) {
                    String[] tmp = p.split(" ");
                    for (int l = 0; l < 2; l ++) {
                        sum = false;
                        int el = Math.abs(Integer.parseInt(tmp[l]));
                        int r = a[el - 1];
                        boolean tot;
                        tot = r == 1;
                        if (Integer.parseInt(tmp[l]) < 0) tot = !tot;
                        if (tot) {
                            sum = true;
                            break;
                        }
                    }
                    if (!sum) {
                        mult = false;
                        break;
                    }
                }

                if (mult) {
                    System.out.println("NO");
                    System.exit(0);
                }

                next_args(a);
            }
            System.out.println("YES");
    }
}
