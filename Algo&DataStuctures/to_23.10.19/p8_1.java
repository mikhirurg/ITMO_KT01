import java.util.Scanner;

public class p8_1 {
    static class listElement{
        listElement next;
        int data;
        private int sum;
    }
    static class stackSum {
       private listElement top = null;
        void push(int x){
            if (top == null){
                listElement e = new listElement();
                e.data = x;
                e.sum = x;
                e.next = top;
                top = e;
            } else {
                listElement e = new listElement();
                e.data = x;
                e.sum = top.sum + x;
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

        Integer getSum(){
            if (top != null){
                int data = top.sum;
                return data;
            }
            return null;
        }
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        stackSum st = new stackSum();
        for (int i=0;i<n;i++){
            st.push(s.nextInt());
            System.out.println(st.getSum());
        }
        for (int i=0;i<n;i++){
            st.pop();
            System.out.println(st.getSum());
        }
    }
}
