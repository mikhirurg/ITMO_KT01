import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class B_lab2 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("bwt.in"));
        String s = in.next();
        in.close();
        char[] ch = s.toCharArray();
        s = s + s;
        char[][] matrix = new char[ch.length][ch.length];
        matrix[0] = ch;
        int shift = 1;
        String[] str = new String[ch.length];
        str[0] = s;
        for (int i = 1; i < ch.length; i++){
            for (int j = shift; j < ch.length+shift; j++){
                matrix[i][j-shift] = s.charAt(j);
            }
            str[i] = new String(matrix[i]);
            shift++;
        }
        Arrays.sort(str);
        StringBuilder ans = new StringBuilder();
        FileWriter fw = new FileWriter("bwt.out");
        for (int i = 0; i < ch.length; i++){
            ans.append(str[i].charAt(str[i].length()-1));
        }
        fw.write(ans.toString());
        fw.close();
    }
}
