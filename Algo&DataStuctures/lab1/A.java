import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] a = new int[n];
        for (int i=0;i<n;i++){
            a[i] = s.nextInt();
        }
        for (int i=1; i<n; i++){
            int j = i;
            while (j>0 && a[j]<a[j-1]){
                int tmp = a[j];
                a[j] = a[j-1];
                a[j-1] = tmp;
                j--;
            }
        }
        for (int i=0; i<n; i++){
            System.out.print(a[i]+" ");
        }
    }
}
