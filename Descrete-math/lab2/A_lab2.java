import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class A_lab2 {
    static class elem{
        elem left;
        elem right;
        long val;
        int id = 0;
        elem(elem right, elem left, long val, int id){
            this.right  = right;
            this.left = left;
            this.val = val;
            this.id = id;
        }
    }
    static int ct = 0;
    static int[] ves;
    static void findAns(elem v, int count){
        if (v.left == null && v.right==null){
            ves[v.id] = count;
        } else {
            if (v.left!=null){
                findAns(v.left, count+1);
            }
            findAns(v.right, count+1);
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("huffman.in"));
        int n = in.nextInt();
        ArrayList<elem> p = new ArrayList<>();
        ves = new int[n];
        int[] el = new int[n];

        for (int i = 0; i < n; i++){
            int tmp = in.nextInt();
            p.add(new elem(null,null,tmp,i));
            el[i] = tmp;
        }
        in.close();
        if (n==1){
            System.out.println(el[0]);
            System.exit(0);
        }
        while (p.size()>1){
            p.sort((o1, o2) -> {
                if (o1.val > o2.val) {
                    return 1;
                } else {
                    if (o1.val == o2.val) return 0;
                    return -1;
                }
            });
            elem e = new elem(p.get(0),p.get(1),p.get(0).val+p.get(1).val,-1);
            p.remove(1);
            p.remove(0);
            p.add(0,e);
        }
        findAns(p.get(0),0);
        BigInteger answ = new BigInteger("0");
        for (int i = 0; i < n; i++){
            answ = answ.add(new BigInteger(Integer.toString(ves[i])).multiply(new BigInteger(Integer.toString(el[i]))));
        }
        FileWriter fw = new FileWriter("huffman.out");
        fw.write(answ.toString());
        fw.close();
    }
}
