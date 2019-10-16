import java.util.*;
public class SumLong {

    public static void main(String args[]) {
        long sum = 0;
        long tmp = 0;
        int is_min = 1;
        int is_zero = 0;
        int hex_mode = 0;
        int digit = 0;
        for (int i = 0; i < args.length; i++) {
            for (char c : args[i].toCharArray()){
               if (!Character.isWhitespace(c)) {
                   if (c == '-') {
                       is_min = -1;
                   } else {
                       if (c == '+') {
                           is_min = 1;
                       } else {
                           if (c == '0') is_zero = 1;
                           if (c == 'x' && is_zero == 1) hex_mode = 1;
                           if (hex_mode == 0) {
                             tmp = tmp * 10 + Long.parseLong(c+"");
                           } else {
                             switch(c){
                               case '0': digit = 0;
                               case '1': digit = 1;
                               case '2': digit = 2;
                               case '3': digit = 3;
                               case '4': digit = 4;
                               case '5': digit = 5;
                               case '6': digit = 6;
                               case '7': digit = 7;
                               case '8': digit = 8;
                               case '9': digit = 9;
                               case 'a': digit = 10;
                               case 'b': digit = 11;
                               case 'c': digit = 12;
                               case 'd': digit = 13;
                               case 'e': digit = 14;
                               case 'f': digit = 15;
                             }
                             tmp = tmp * 10 + digit;
                           }
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
