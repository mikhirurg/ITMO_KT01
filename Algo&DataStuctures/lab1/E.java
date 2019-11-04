import java.util.Scanner;

public class E {
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
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Integer a[] = new Integer[n];
        for (int i = 0; i<n; i++){
            a[i] = in.nextInt();
        }
        a = sort(a);
        int k = in.nextInt();
        for (int i = 0; i<k;i++){
            int left = in.nextInt();
            int right = in.nextInt();
            int l = -1;
            int r = a.length;
            int le = 0;
            int ri = 0;
            int mid = 0;
            while (l<r-1){
                mid = (r+l)/2;
                if (a[mid]<left){
                    l=mid;
                } else {
                    r=mid;
                }
            }
            le = r;
            l = -1;
            r = a.length;
            while (l<r-1){
                mid = (r+l)/2;
                if (a[mid]<=right){
                    l=mid;
                } else {
                    r=mid;
                }
            }
            ri = r;

            System.out.println(ri - le);
        }
    }
}
