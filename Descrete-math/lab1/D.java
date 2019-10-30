import java.util.Scanner;

public class D {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++){
            a[i] = in.nextLong();
        }
        long s = in.nextLong();
        boolean is_dnf = false;
        for (int i = 0; i < n; i++){
            if (a[i] == s){
                is_dnf=true;
                break;
            }
        }
        if (is_dnf){
            long tmp = s;
            int num = n;

            StringBuilder answ = new StringBuilder();
            for (int i = 0; i < n; i++){
                if (s%2==0){
                    answ.append(num);
                } else {
                    answ.append("~"+num);
                }
                if (num>0) {
                    answ.append("&");
                }
                num--;
                s /= 2;
            }
            System.out.println(answ.toString());
        } else {
            System.out.println("Impossible");
        }
    }
}
