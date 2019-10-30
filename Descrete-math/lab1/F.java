import java.util.Scanner;

public class F {
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
    static void print(int[] val) {
        for (int i = 0; i < val.length; i++) {
            System.out.print(val[i]);
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int len = (int)Math.pow(2,n);
        int a[][] = new int[len][len];
        for (int i = 0; i < len; i++){
            in.next();
            a[0][i] = in.nextInt();
        }
        int k = 1;
        for (int j = 1; j<len; j++){
            a[j] = new int[len-k];
            for (int i = 0; i < len - k; i++){
                a[j][i] = (a[j-1][i] + a[j-1][i+1])%2;
            }
            k++;
        }
        int[] arg = new int[n];
        for (int i=0; i<Math.pow(2,n);i++){
            print(arg);
            System.out.println(" "+a[i][0]);
            next_args(arg);
        }


    }
}
