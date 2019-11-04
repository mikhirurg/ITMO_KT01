import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class D_lab2 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("lzw.in"));
        String s = in.nextLine();
        StringBuilder t = new StringBuilder();
        ArrayList<String> alphabet = new ArrayList(Arrays.asList("abcdefghijklmnopqrstuvwxyz".split("")));
        StringBuilder answ = new StringBuilder();
        for (int i = 0; i < s.length(); i++){
            t.append(s.charAt(i));
            if (!alphabet.contains(t.toString())){
                alphabet.add(t.toString());

                answ.append(alphabet.indexOf(t.toString().substring(0,t.length()-1)));
                answ.append(" ");

                t = new StringBuilder();
                t.append(s.charAt(i));
            }
        }

        answ.append(alphabet.indexOf(t.toString()));
        FileWriter fw = new FileWriter("lzw.out");
        fw.write(answ.toString());
        fw.close();
    }
}
