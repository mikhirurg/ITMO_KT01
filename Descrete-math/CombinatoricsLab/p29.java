import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class p29 {
    static FileWriter fw;

    static {
        try {
            fw = new FileWriter("nextpartition.out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void gen(int n, ArrayList<Integer> part) throws IOException {
        int x1 = part.get(part.size()-1);
        int x2 = part.get(part.size()-2);
        part.set(part.size()-1, x1-1);
        part.set(part.size()-2, x2+1);
        if (part.get(part.size()-2) > part.get(part.size()-1)) {
            part.set(part.size()-2, x1+x2);
            part.remove(part.size()-1);
        } else {
            while (part.get(part.size()-2)*2 <= part.get(part.size()-1)) {
                part.add(part.get(part.size()-1) - part.get(part.size()-2));
                part.set(part.size()-2,part.get(part.size()-3));
            }
        }
        StringBuilder ans = new StringBuilder();
        ans.append(n).append("=");
        ans.append(part.get(0));
        for (int i = 1; i < part.size(); i++) {
            ans.append("+").append(part.get(i));
        }
        fw.write(ans.toString());
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("nextpartition.in"));
        String input = in.nextLine();
        String[] numb = input.split("[+=]");
        int n = Integer.parseInt(numb[0]);
        ArrayList<Integer> arl = new ArrayList<>();
        for (int i = 1; i < numb.length; i++) {
            arl.add(Integer.parseInt(numb[i]));
        }
        if (arl.size()<2) {
            fw.write("No solution");
        } else {
            gen(n, arl);
        }
        fw.close();
    }
}
