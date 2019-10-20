import java.util.Scanner;

public class Reverse {
    public static void main(String[] args) {
        MyScanner3 in = new MyScanner3(System.in);
        Object list[] = null;
        while (in.hasNextLine()){
            String line = in.nextLine();
            MyScanner3 s2 = new MyScanner3(line);
            Object[] bin = null;
            int count = 0;
            while (s2.hasNextInt()){
                bin = new Object[]{s2.nextInt(),bin};
                count++;
            }
            int []a = new int[count];
            for (int i = 0; i<a.length; i++){
                a[i] = (Integer) bin[0];
                bin = (Object[]) bin[1];
            }
            list = new Object[] {a,list};
        }
        while (list!=null){
            int[] bin = (int[]) list[0];
            for (int i = 0; i<bin.length; i++){
                System.out.print(bin[i]+" ");
            }
            list = (Object[]) list[1];
            System.out.println();
        }
        System.out.flush();
    }
}
