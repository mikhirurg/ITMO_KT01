import java.util.Scanner;

public class K2 {

    static int [] v;
    static int [] w;
    static int [] idx;
    static double m;
    static double l;
    static double r;


    static int partition(int begin, int end) {
        int pivot = end;

        int counter = begin;
        for (int i = begin; i < end; i++) {
            if (v[idx[i]] - m*w[idx[i]] < v[idx[pivot]] - m*w[idx[pivot]]) {
                int temp = idx[counter];
                idx[counter] = idx[i];
                idx[i] = temp;
                counter++;
            }
        }
        int temp = idx[pivot];
        idx[pivot] = idx[counter];
        idx[counter] = temp;

        return counter;
    }

    public static void quickSort(int begin, int end) {
        if (end <= begin) return;
        int pivot = partition(begin, end);
        quickSort(begin, pivot-1);
        quickSort(pivot+1, end);
    }

    public static void quickSort() {
        quickSort(0, idx.length-1);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int k = s.nextInt();

        v = new int[n];
        w = new int[n];
        idx = new int[n];

        int mxv = 0;
        int mnw = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            v[i] = s.nextInt();
            if (v[i] > mxv) mxv = v[i];
            w[i] = s.nextInt();
            if (w[i] < mnw) mnw = w[i];
            idx[i] = i;
        }

        r = ((double)(mxv*k))/mnw;
        l = 0;
        m = (l + r) / 2.0;
        double om = Double.MAX_VALUE;
        double cm = m;
        while (om != cm) {
            quickSort();
            int sv = 0;
            int sw = 0;
            for (int j = 0; j < k; j++) {
                sv += v[idx[j]];
                sw += w[idx[j]];
            }
            om = cm;
            cm = (double) sv / sw;
            if (cm > m) {
                m = (m + r) / 2.0;
                l = m;
            }
        }

        for (int j = 0; j < k; j++) {
            System.out.println(idx[j] + 1);
        }
    }
}

