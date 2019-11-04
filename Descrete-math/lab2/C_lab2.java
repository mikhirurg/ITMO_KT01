import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class C_lab2 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("mtf.in"));
        //Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        in.close();
        ArrayList<String> alphabet = new ArrayList(Arrays.asList("abcdefghijklmnopqrstuvwxyz".split("")));
        StringBuilder answ = new StringBuilder();
        for (int i = 0; i < s.length(); i++){
            int p = alphabet.indexOf(s.charAt(i)+"");
            String a = alphabet.get(p);
            alphabet.remove(a);
            alphabet.add(0,a);
            answ.append(p+1);
            answ.append(" ");
        }
        FileWriter fw = new FileWriter("mtf.out");
        fw.write(answ.toString());
        fw.close();
        //System.out.println(answ.toString());
    }
}
