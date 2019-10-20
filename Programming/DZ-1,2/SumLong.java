import java.util.*;
public class SumLong {

    public static void main(String args[]) {
        long sum = 0;
        long tmp = 0;
        int is_min = 1;
        for (int i = 0; i < args.length; i++) {
            for (char c : args[i].toCharArray()){
               if (!Character.isWhitespace(c)) {
                   if (c == '-') {
                       is_min = -1;
                   } else {
                       if (c == '+') {
                           is_min = 1;
                       } else {
                           tmp = tmp * 10 + Long.parseLong(c+"");
                       }
                   }
               } else {
                   tmp = tmp * is_min;
                   sum += tmp;
                   tmp = 0;
                   is_min = 1;
               }
            }
            tmp = tmp * is_min;
            sum += tmp;
            tmp = 0;
            is_min = 1;
	  }
        System.out.println(sum);
    }

}
