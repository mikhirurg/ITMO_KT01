import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class K {
    static int partition(int[][] a, int l, int r, double M){
        int[] v = a[(l+r)/2];
        int i = l;
        int j = r;
        while (i <= j){
            while (a[i][0]-M*(a[i][1]) > v[0]-M*v[1]){
                i++;
            }
            while (a[j][0]-M*(a[j][1]) < v[0]-M*v[1]){
                j--;
            }
            if (i>=j) break;
            int[] tmp = new int[2];
            tmp[0] = a[i][0];
            tmp[1] = a[i][1];
            a[i][0] = a[j][0];
            a[i][1] = a[j][1];
            a[j][0] = tmp[0];
            a[j][1] = tmp[1];
        }
        return j;
    }
    static int[] orderStat(int[][] a, int k, double M){
        int l = 0;
        int r = a.length-1;
        while (true) {
            int mid = partition(a, l, r, M);
            if (mid == k) {
                return a[mid];
            } else if (k < mid) {
                r = mid;
            } else l = mid;
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[][] a = new int[n][2];
        int[][] b = new int[n][2];
        double M = 0;
        int maxv = 0;
        int minw = Integer.MAX_VALUE;
        for (int i=0; i<n; i++){
            a[i][0] = in.nextInt();
            a[i][1] = in.nextInt();
            if (a[i][0]>maxv) maxv = a[i][0];
            if (a[i][1]<minw) minw = a[i][1];
        }
        M = ((double)maxv/minw)*k;
        double l = 0;
        double r = M;
        double eps = 0.0001;
        ArrayList<Integer> ans = new ArrayList();
        while (l<r-eps){
            double mid = (l+r)/2;
            double sumV = 0;
            double sumW = 0;
            int count = 0;

            for (int i=0;i<n ;i++){
                b[i][0] = a[i][0];
                b[i][1] = a[i][1];
            }
            int[] nkStat = orderStat(b,n-k,M);
            ans.clear();
            for (int i = 0; i<n; i++){
                if (a[i][0]-mid*a[i][1] >= nkStat[0]-mid*nkStat[1]) {
                    count++;
                    sumV += a[i][0];
                    sumW += a[i][1];
                    ans.add(i);
                }
            }
            if (sumV/sumW > mid && count>=k) {
                l = mid;
            } else r = mid;
        }
        int len = Math.min(ans.size(),k);
        for (int i=0; i<len; i++){
            System.out.println(ans.get(i)+1);
        }
    }
}
