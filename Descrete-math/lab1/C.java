import java.util.Scanner;
import java.util.Vector;

public class C {

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

    static boolean isSaves0(String s) {
        if (s.charAt(0) == '0') return true;
        return false;
    }

    static boolean isSaves1(String s) {
        if (s.charAt(s.length() - 1) == '1') return true;
        return false;
    }

    static boolean isSd(String s, int args) {
        if (args == 0) return false;
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) == s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    static boolean is2pow(int n) {
        while (n > 0) {
            if (n != 1 && n % 2 == 1) return false;
            n = n / 2;
        }
        return true;
    }

    static boolean isMono(String s, int args) {
        if (args == 0) return true;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++){
                int a = i;
                int b = j;
                for (int k = 0; k < args; k++){
                    if (a%2>b%2){
                        break;
                    }
                    a /= 2;
                    b /= 2;
                }
                if (a==0 && b==0) {
                    if (s.charAt(i) > s.charAt(j)) return false;
                }
            }
        }
        return true;
    }

    static boolean isLinear(String s, int args) {
        if (args == 0) return true;
        int len = s.length();
        int a[][] = new int[len][len];
        for (int i = 0; i < len; i++) {
            a[0][i] = Integer.parseInt(s.charAt(i) + "");
        }
        int k = 1;
        for (int j = 1; j < len; j++) {
            a[j] = new int[len - k];
            for (int i = 0; i < len - k; i++) {
                a[j][i] = (a[j - 1][i] + a[j - 1][i + 1]) % 2;
            }
            k++;
        }
        a[0][0] = 0;
        for (int i = 1; i < len; i*=2) {
            a[i][0] = 0;
        }
        for (int i = 0; i < len; i++) {
            if (a[i][0] == 1) {
                return false;
            }
        }

        return true;
    }

    static class Pair {
        int num_args;
        String tbl;

        Pair(int num_args, String tbl) {
            this.num_args = num_args;
            this.tbl = tbl;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Pair[] set = new Pair[n];
        for (int i = 0; i < n; i++) {
            int arg = in.nextInt();
            String s = in.next();
            set[i] = new Pair(arg, s);
        }
        int i = 0;
        for (i = 0; i < n; i++) {
            if (!isSaves0(set[i].tbl)) {
                break;
            }
        }
        if (i == n) {
            System.out.println("NO");
            System.exit(0);
        }
        i = 0;
        for (i = 0; i < n; i++) {
            if (!isSaves1(set[i].tbl)) {
                break;
            }
        }
        if (i == n) {
            System.out.println("NO");
            System.exit(0);
        }
        i = 0;
        for (i = 0; i<n; i++){
            if (!(isMono(set[i].tbl, set[i].num_args))){
                break;
            }
        }
        if (i == n) {
            System.out.println("NO");
            System.exit(0);
        }
        i = 0;
        for (i = 0; i < n; i++) {
            if (!isSd(set[i].tbl, set[i].num_args)) {
                break;
            }
        }
        if (i == n) {
            System.out.println("NO");
            System.exit(0);
        }
        i = 0;
        for (i = 0; i < n; i++) {
            if (!isLinear(set[i].tbl, set[i].num_args)) {
                break;
            }
        }
        if (i == n) {
            System.out.println("NO");
            System.exit(0);
        }
        System.out.println("YES");
    }
}
