import java.util.Scanner;

public class p8_2 {
    static class listElement{
        listElement next;
        int data;
        private int min;
    }
    static class stackMin {
        private listElement top = null;
        void push(int x){
            if (top == null){
                listElement e = new listElement();
                e.data = x;
                e.min = x;
                e.next = top;
                top = e;
            } else {
                listElement e = new listElement();
                e.data = x;
                e.min = Math.min(top.min , x);
                e.next = top;
                top = e;
            }
        }

        Integer pop(){
            if (top != null){
                int data = top.data;
                top = top.next;
                return data;
            }
            return null;
        }

        Integer getMin(){
            if (top != null){
                int data = top.min;
                return data;
            }
            return null;
        }
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        stackMin st = new stackMin();
        for (int i=0;i<n;i++){
            st.push(s.nextInt());
            System.out.println(st.getMin());
        }
        for (int i=0;i<n;i++){
            st.pop();
            System.out.println(st.getMin());
        }
    }
}
