import java.util.*;

import static java.lang.Character.*;

public class SumDouble {
    public static void main(String args[]) {
        double sum = 0;
        int r = 0;
        int l = 0;
            for (String j : args) {
                r = 0;
                l = 0;
                while (r < j.length()) {
                    while (l < j.length() && Character.isWhitespace(j.charAt(l))) l++;
                    r = l;
                    while (r < j.length() && !Character.isWhitespace(j.charAt(r))) {
                        r++;
                    }
                    if(r <= j.length() && r - l > 0){
                        sum = sum + Double.parseDouble(j.substring(l, r));
                    }
                    l = r;
                }
            }
        System.out.println(sum);
    }
}
