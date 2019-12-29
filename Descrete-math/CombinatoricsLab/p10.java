import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class p10 {
    static FileWriter fw;
    static int[] a;
    static {
        try {
            fw = new FileWriter("partition.out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static List<String> partition = new ArrayList<>();
    static void gen(int pos, int n, int sum) {
        if (sum == 0) {
            StringBuilder out = new StringBuilder();
            for (int i = pos-1; i >= 0; i--) {
                out.append(a[i]);
                if (i!=0) {
                    out.append("+");
                }
            }
            partition.add(out.toString());
        } else {
            for (int c = 1; c <= Math.min((pos == 0 ? n : a[pos-1]), sum); c++) {
                a[pos] = c;
                gen(pos+1, n, sum - c);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("partition.in"));
        int n = in.nextInt();
        a = new int[n];
        gen(0, n, n);
        for (int i = 0; i < partition.size(); i++) {
            fw.write(partition.get(i));
            if (i != partition.size()-1) {
                fw.write("\n");
            }
        }
        fw.close();
    }
}
