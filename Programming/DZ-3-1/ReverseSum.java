import java.util.ArrayList;
import java.util.Scanner;

public class ReverseSum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> arl = new ArrayList<>();
        int line = 0;
        while (in.hasNextLine()) {
            String st = in.nextLine();
            Scanner in2 = new Scanner(st);
            arl.add(new ArrayList<>());
            while (in2.hasNextInt()) {
                int tmp = in2.nextInt();
                arl.get(line).add(tmp);
            }
            line++;
            in2.close();
        }
        in.close();
        ArrayList<ArrayList<Integer>> arl2 = new ArrayList<>();
        int line2 = 0;
        for (int i = 0; i < line; i++) {
            arl2.add(new ArrayList<>());
            for (int j = 0; j < arl.get(i).size(); j++) {
                int sum = 0;
                for (int l = 0; l < line; l++) {
                    if (arl.get(l).size() > j) {
                        sum += arl.get(l).get(j);
                    }
                }
                for (int l = 0; l < arl.get(i).size(); l++) {
                    if (arl.get(i).size() > l) {
                        sum += arl.get(i).get(l);
                    }
                }
                sum -= arl.get(i).get(j);
                arl2.get(line2).add(sum);
            }
            line2++;
        }
        for (int i = 0; i < line2; i++) {
            for (int j = 0; j < arl.get(i).size(); j++) {
                System.out.print(arl2.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}
