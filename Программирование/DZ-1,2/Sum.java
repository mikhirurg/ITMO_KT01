import java.util.*;
public class Sum {
    
    public static void main(String args[]) {
        int sum = 0;
        int tmp = 0;
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
                           tmp = tmp * 10 + Integer.parseInt(c+"");
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