import java.util.Scanner;

public class G {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n =in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        n--;
        int l = 0;
        int r = n*Math.max(x,y);
        int delta = Math.min(x,y);
        while (l<r-1){
            int mid = (l+r)/2;
            if ((mid/x)+(mid/y)<n){
                l = mid;
            } else {
                r = mid;
            }
        }
        System.out.println(r+delta);
    }
}
