import java.util.Scanner;

public class F {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n=s.nextInt();
        int k=s.nextInt();
        int a[] = new int[n];
        int b[] = new int[k];
        for (int i=0;i<n;i++){
            a[i]=s.nextInt();
        }
        for (int i=0;i<k;i++){
            b[i]=s.nextInt();
        }

        for (int i=0;i<k;i++){
            int t = b[i];
            int r=n-1;
            int l=0;
            int m=(l+r)/2;
            while (l<r){
                m=(l+r)/2;
                if (a[m]<t) l=m; else r=m;
                if (a[m]==t){
                    System.out.println(a[m]);
                    break;
                } else {
                    if (l == r) {
                        System.out.println(a[r]);
                        break;
                    } else {
                        if (Math.abs(l - r) == 1) {
                            if (Math.abs(a[l] - t) > Math.abs(a[r] - t)) {
                                System.out.println(a[r]);
                                break;
                            } else {
                                if (Math.abs(a[l] - t) < Math.abs(a[r] - t)) {
                                    System.out.println(a[l]);
                                    break;
                                } else {
                                    if (Math.abs(a[l] - t) == Math.abs(a[r] - t)) {
                                        System.out.println(Math.min(a[l], a[r]));
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}