import java.util.Scanner;

public class C {
    static long count = 0;

    static Integer[] merge(Integer[] a, Integer[] b) {
        int j = 0;
        int i = 0;
        Integer[] c = new Integer[a.length + b.length];
        while (i + j < c.length) {
            if ((j == b.length) || (i < a.length && a[i] <= b[j])) {
                c[i + j] = a[i];
                i++;
            } else {
                c[i + j] = b[j];
                j++;
                count+=(a.length-i);
            }
        }
        return c;
    }

    static Integer[] sort(Integer[] a) {
        if (a.length > 1) {
            int n = a.length;
            int mid = n / 2;
            Integer[] l = new Integer[n / 2];
            Integer[] r = new Integer[n / 2 + n%2];
            for (int i = 0; i < mid; i++) {
                l[i] = a[i];
            }
            for (int i = 0; i < n - mid; i++) {
                r[i] = a[i + mid];
            }
            l = sort(l);
            r = sort(r);
            a = merge(l, r);
        }
        return a;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = s.nextInt();
        }
        a = sort(a);

        System.out.println(count);

    }
}
