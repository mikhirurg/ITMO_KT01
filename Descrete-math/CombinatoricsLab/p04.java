import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class p04 {
    static FileWriter fw;
    static int count = 0;
    static void genChainCode(int n) throws IOException {
        StringBuilder current = new StringBuilder();
        for (int i = 0; i < n; i++) {
            current.append("0");
        }
        Set<String> result = new HashSet<>();
        while (true) {
            StringBuilder prefix = new StringBuilder(current.subSequence(1, n));
            if (!result.contains(prefix.toString()+"1")) {
                current = prefix.append('1');
            } else {
                if (!result.contains(prefix.toString()+"0")) {
                    current = prefix.append('0');
                } else break;
            }
            result.add(current.toString());
            count++;
            fw.write(current.toString());
            if (count != (1 << n)) {
                fw.write("\n");
            }

        }
    }
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("chaincode.in"));
        int n = in.nextInt();
        fw = new FileWriter("chaincode.out");
        genChainCode(n);
        fw.close();
    }
}
