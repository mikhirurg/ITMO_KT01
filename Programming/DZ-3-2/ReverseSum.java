import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ReverseSum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int [][] ar = new int[1][1];
        int [] size = new int[1];
        int line = 0;
        while (in.hasNextLine()) {
            if (line>ar.length-1) {
                ar = Arrays.copyOf(ar,ar.length*2);
                size = Arrays.copyOf(size, size.length*2);
            }
            String st = in.nextLine();
            Scanner in2 = new Scanner(st);
            int len = 0;
            ar[line] = new int[1];
            while (in2.hasNextInt()) {
                if (len > ar[line].length-1){
                    ar[line] = Arrays.copyOf(ar[line],ar[line].length*2);
                }
                int tmp = in2.nextInt();
                ar[line][len] = tmp;
                len++;
            }
            size[line] = len;
            line++;
            in2.close();
        }
        in.close();
        int line2 = 0;
        int [][] ar2 = new int[1][];
        for (int i = 0; i < line; i++) {
            if (line2>ar2.length-1) {
                ar2 = Arrays.copyOf(ar2,ar2.length*2);
            }
            ar2[line2] = new int[ar[line2].length];
            for (int j = 0; j < size[i]; j++) {
                int sum = 0;
                for (int l = 0; l < line; l++) {
                    if (ar[l].length > j) {
                        sum += ar[l][j];
                    }
                }
                for (int l = 0; l < size[i]; l++) {
                    if (ar[i].length > l) {
                        sum += ar[i][l];
                    }
                }
                sum -= ar[i][j];
                ar2[line2][j]=sum;
            }
            line2++;
        }
        for (int i = 0; i < line2; i++) {
            for (int j = 0; j < size[i]; j++) {
                System.out.print(ar2[i][j] + " ");
            }
            System.out.println();
        }
    }
}
