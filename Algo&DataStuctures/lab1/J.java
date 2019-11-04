import java.util.Scanner;

public class J {
    static double hyp(double a, double b){
        return Math.sqrt(a*a+b*b);
    }
    static double f(double x, double a, double vp, double vf,double scale){
        return hyp(scale-a, x) / vp + (hyp(a,scale-x)) / vf;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double scale = 10000;
        double vp = in.nextDouble();
        double vf = in.nextDouble();
        double a = in.nextDouble()*scale;
        double l = 0.0;
        double r = 1.0*scale;
        double eps = 0.0001*scale;
        while (r-l>eps) {
            double m1 = (l*2+r) / 3;
            double m2 = (l+r*2) / 3;
            if (f(m1,a,vp,vf,scale) < f(m2,a,vp,vf,scale)) {
                r = m2;
            } else l = m1;
        }
        double ans = ((l+r)/2)/scale;
        System.out.println(ans);
    }
}
