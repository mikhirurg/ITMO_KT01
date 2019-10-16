import java.io.IOException;
import java.util.Arrays;

public class ReverseMin {
    public static void main(String[] args) throws IOException {
        MyScanner in = new MyScanner(System.in);
        int[][] ar = new int[1][1];
        int[] size = new int[1];
        int line = 0;
        int maxSize = 0;
        while (in.hasNextChar()) {
            if (line > ar.length - 1) {
                ar = Arrays.copyOf(ar, ar.length * 2);
                size = Arrays.copyOf(size, size.length * 2);
            }
            String st = in.readLine();
            MyScanner in2 = new MyScanner(st);
            int len = 0;
            ar[line] = new int[1];
            while (in2.hasNext()) {
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
        }
        int[] cols = new int[maxSize];
        int[] rows = new int[line];
        Arrays.fill(cols,Integer.MAX_VALUE);
        Arrays.fill(rows,Integer.MAX_VALUE);
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < size[i]; j++) {
                rows[i] = Math.min(rows[i], ar[i][j]);
                cols[j] = Math.min(cols[j], ar[i][j]);
            }
        }
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < size[i]; j++) {
                int out = Math.min(rows[i], cols[j]);
                System.out.print(out + " ");
            }
            System.out.println();
        }
    }
}
