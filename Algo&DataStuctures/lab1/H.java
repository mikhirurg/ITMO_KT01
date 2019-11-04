import java.util.Scanner;

public class H {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long w = in.nextInt();
        long h = in.nextInt();
        long n = in.nextInt();
        long l = Math.min(w,h);
        long r = Math.max(w,h)*n;
        while (r > l) {
            long mid = (r+l)/2;
            if (n <= (mid / w) * (mid / h)) {
                r = mid;
            } else {
                l = mid+1;
            }
        }
        System.out.println(l);
    }
}
