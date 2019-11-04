import java.util.Scanner;

public class I {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double c = in.nextDouble();
        double l = 0;
        double r = c;
        double x = 0;
        double eps = 0.000001;
        while (l<r-eps) {
            double mid = (l+r)/2;
            if (mid*mid + Math.sqrt(mid) < c){
                l = mid;
            } else r = mid;
        }

        System.out.println(r);
    }
}
