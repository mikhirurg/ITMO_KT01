import java.util.ArrayList;
import java.util.Scanner;

public class E {
    static class node{
        int val;
    }

    static class function{
        ArrayList<Object> val = new ArrayList<>();
        String result;
        int getVal(){
            int n = 0;
            int pow = 1;
            for (int i = val.size()-1; i >= 0; i--){
                if (val.get(i) instanceof node){
                    n +=((node)val.get(i)).val * pow;
                } else {
                    n +=((function)val.get(i)).getVal() * pow;
                }
                pow*=2;
            }
            return Integer.parseInt(result.charAt(n)+"");
        }
    }

    static int maxLen = Integer.MIN_VALUE;

    static void getLen(Object f, int len){
        if (f instanceof node){
            maxLen = Math.max(maxLen, len);
        } else {
            for (Object obj : ((function)f).val){
                getLen(obj,len+1);
            }
        }
    }
    static void set0(ArrayList<node> vals){
        for (int i=0; i<vals.size(); i++){
            vals.get(i).val = 0;
        }
    }
    static void nextArgs(ArrayList<node> vals){
        int start = vals.size()-1;
        vals.get(start).val++;
        while (vals.get(start).val==2 && start > 0){
            if (vals.get(start).val > 1){
                vals.get(start).val = 0;
                vals.get(start-1).val++;
                start--;
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ArrayList<Object> nodes = new ArrayList<>();
        ArrayList<node> vals = new ArrayList<>();
        for (int i = 0; i<n;i++){
            int m = in.nextInt();
            if (m == 0){
                nodes.add(new node());
                vals.add((node)nodes.get(nodes.size()-1));
            } else {
                function f = new function();
                for (int k = 0; k<m; k++){
                    f.val.add(nodes.get(in.nextInt()-1));
                }
                StringBuilder val = new StringBuilder();
                for (int k = 0; k < 1<<m; k++){
                    val.append(in.nextInt());
                }
                f.result = val.toString();
                nodes.add(f);
            }
        }

        getLen(nodes.get(nodes.size()-1), 0);

        System.out.println(maxLen);
        StringBuilder answ = new StringBuilder();

        set0(vals);

        Object head = nodes.get(nodes.size()-1);

        for (int i = 0; i < 1<<vals.size(); i++){
            if (head instanceof function){
                answ.append(((function) head).getVal());
            } else {
                answ.append(((node)head).val);
            }
            nextArgs(vals);
        }
        System.out.println(answ.toString());
    }
}
