import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int []a = new int[101];
        while (s.hasNextInt()) {
            a[s.nextInt()]++;
        }
        for (int i = 0; i <= 100; i++){
            for (int j = 0; j < a[i]; j++){
                System.out.print(i+" ");
            }
        }
    }
}
