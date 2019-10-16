import java.util.*;

import static java.lang.Character.*;

public class SumDouble {
    static boolean is_wire(char c) {
        return Character.isWhitespace(c);
    }

    public static void main(String args[]) {
        double sum = 0;
        int r = 0;
        for (String j : args) {
            for (int i = 0; i < j.length(); i++) {
                if (!is_wire(j.charAt(i))) {
                    r = i;
                    while (r < j.length() && !is_wire(j.charAt(r))) {
                        r++;
                    }
                    sum = sum + Double.parseDouble(j.substring(i, r));
                }
            }
        }
        System.out.println(sum);
    }
}
