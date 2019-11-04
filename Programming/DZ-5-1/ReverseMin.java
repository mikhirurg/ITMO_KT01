import java.io.IOException;
import java.util.Arrays;

public class ReverseMin {
    final static int DF_SIZE = 1;

    public static void main(String[] args) throws IOException {
        MyScanner in = new MyScanner(System.in);
        int[][] matrix = new int[DF_SIZE][DF_SIZE];
        int[] size = new int[DF_SIZE];
        int line = 0;
        int maxSize = 0;
        while (in.hasNextChar()) {
            if (line > matrix.length - 1) {
                matrix = Arrays.copyOf(matrix, matrix.length * 2);
                size = Arrays.copyOf(size, size.length * 2);
            }
            String st = in.readLine();
            MyScanner in2 = new MyScanner(st);
            int len = 0;
            matrix[line] = new int[1];
            while (in2.hasNext()) {
                if (len > matrix[line].length - 1) {
                    matrix[line] = Arrays.copyOf(matrix[line], matrix[line].length * 2);
                }
                int tmp = in2.nextInt();
                matrix[line][len] = tmp;
                len++;
            }
            size[line] = len;
            if (maxSize < len) maxSize = len;
            line++;
        }
        int[] cols = new int[maxSize];
        int[] rows = new int[line];
        Arrays.fill(cols, Integer.MAX_VALUE);
        Arrays.fill(rows, Integer.MAX_VALUE);
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < size[i]; j++) {
                rows[i] = Math.min(rows[i], matrix[i][j]);
                cols[j] = Math.min(cols[j], matrix[i][j]);
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
