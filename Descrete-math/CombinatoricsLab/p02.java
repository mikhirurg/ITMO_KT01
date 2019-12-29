import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class p02 {
    static String getNum(int a, int n){
        StringBuilder build = new StringBuilder();
        int tmp = a;
        for (int i = 0; i < n; i++){
            build.append(tmp%2);
            tmp /= 2;
        }
        build = build.reverse();
        return build.toString();
    }
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("gray.in"));
        int a = in.nextInt();
        FileWriter fw = new FileWriter(new File("gray.out"));
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i<1<<a; i++){
            ans.append(getNum(i ^ (i/2), a));
            if (i != (1<<a)-1) {
                ans.append("\n");
            }
            fw.write(ans.toString());
            ans.delete(0, ans.length());
        }
        fw.close();
    }
}
