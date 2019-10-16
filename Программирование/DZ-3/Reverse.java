import java.util.Scanner;

public class Reverse {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Object list[] = null;
        while (in.hasNextLine()){
            String line = in.nextLine();
            Scanner s2 = new Scanner(line);
            Object[] bin = null;
            while (s2.hasNextInt()){
                bin = new Object[]{s2.nextInt(),bin};
            }
            list = new Object[] {bin,list};
        }
        while (list!=null){
            Object[] bin = (Object[]) list[0];
            while (bin!=null){
                System.out.print(bin[0]+" ");
                bin = (Object[]) bin[1];
            }
            list = (Object[]) list[1];
            System.out.println();
        }
        System.out.flush();
    }
}
