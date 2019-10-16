import java.util.Arrays;
import java.util.Scanner;

public class ReverseSum {
    final static int DF_SIZE = 128;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] ar = new int[DF_SIZE][DF_SIZE];
        int[] size = new int[DF_SIZE];
        int line = 0;
        int maxSize = 0;
        while (in.hasNextLine()) {
            if (line > ar.length - 1) {
                ar = Arrays.copyOf(ar, ar.length * 2);
                size = Arrays.copyOf(size, size.length * 2);
            }
            String st = in.nextLine();
            Scanner in2 = new Scanner(st);
            int len = 0;
            ar[line] = new int[1];
            while (in2.hasNextInt()) {
                if (len > ar[line].length - 1) {
                    ar[line] = Arrays.copyOf(ar[line], ar[line].length * 2);
                }
                int tmp = in2.nextInt();
                ar[line][len] = tmp;
                len++;
            }
            size[line] = len;
            if (maxSize < len) maxSize = len;
            line++;
            in2.close();
        }
        in.close();
        int[] cols = new int[maxSize];
        int[] rows = new int[line];
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < size[i]; j++) {
                rows[i] += ar[i][j];
                cols[j] += ar[i][j];
            }
        }
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < size[i]; j++) {
                int out = rows[i] + cols[j] - ar[i][j];
                System.out.print(out + " ");
            }
            System.out.println();
        }
    }
}
