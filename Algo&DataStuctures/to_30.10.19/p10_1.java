import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class p10_1 {
    static int[] p;
    static int[] max;
    static int[] min;
    static int[] sum;

    static class pair{
        int v1, v2;
        pair(int v1, int v2){
            this.v1 = v1;
            this.v2 = v2;
        }
    }

    static ArrayList<pair>[] verges;

    //p_10_1

    static int getMax(int x){
        return max[find(x)];
    }

    static int getMin(int x){
        return min[find(x)];
    }

    static int getAltSum(int x){
        return sum[find(x)];
    }

    static int getSum(int x){
        int sum = 0;
        for (int i = 1; i < p.length; i++){
            if (p[i] == x){
                sum++;
            }
        }
        return sum;
    }

    static int find(int x){
        return p[x];
    }

    //p10_2

    static int maxComp(int[] p){
        int[] a = new int[p.length];
        for (int i = 1; i < p.length; i++){
            a[p[i]]++;
        }
        int max = 0;
        for (int i = 0; i < p.length; i++){
            if (a[i]>max){
                max = a[i];
            }
        }
        return max;
    }

    //p_10_3

    static void addVerge(int comp1, int comp2, int x, int y){
        for (int i = 0; i < verges[comp1].size(); i++){
            verges[comp2].add(verges[comp1].get(i));
        }
        for (pair p : verges[comp2]){
            if ((p.v1 == x && p.v2 == y) || (p.v1 == y && p.v2 == x)){
                return;
            }
        }
        verges[comp1].clear();
        verges[comp2].add(new pair(x,y));
    }

    static int getVerges(int comp){
        return verges[comp].size();
    }

    static void union(int x, int y){
        int x1 = find(x);
        int y1 = find(y);
        addVerge(x1, y1, x, y);
        for (int i = 1; i < p.length; i++){
            if (p[i] == x1){
                p[i] = y1;
                min[i] = Math.min(getMin(x1), getMin(y1));
                max[i] = Math.max(getMax(x1), getMax(y1));
            }
        }
        int sums = 0;
        for (int i = 1; i<p.length; i++){
            if (p[i]==y1){
                sums+=i;
            }
        }
        for (int i = 1; i < p.length; i++){
            if (p[i]==y1){
                sum[i] = sums;
            }
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt()+1;
        p = new int[n];
        max = new int[n];
        min = new int[n];
        sum = new int[n];
        verges = new ArrayList[n];
        for (int i = 1; i < n; i++){
            p[i] = (i);
            max[i] = (i);
            min[i] = (i);
            sum[i] = (i);
            verges[i] = new ArrayList<>();
        }
        union(2,3);
        union(3,4);
        union(4,5);
        union(5,6);
        union(6, 2);
        System.out.println(getAltSum(6));
        System.out.println(maxComp(p));
        System.out.println(getVerges(6));
    }
}
