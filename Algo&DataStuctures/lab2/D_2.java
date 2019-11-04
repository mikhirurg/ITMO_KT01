import java.util.Scanner;

public class D_2 {
    public static class queue {
        static class listElement {
            listElement next;
            listElement prev;
            long data;
        }

        private listElement head = null;
        private listElement tail = null;
        private listElement center = null;

        private int size = 0;

        void add(long x) {
            size++;
            if (head == null) {
                listElement e = new listElement();
                e.data = x;
                e.next = null;
                e.prev = null;
                head = e;
                tail = e;
                center = e;
            } else {
                listElement e = new listElement();
                e.data = x;
                tail.next = e;
                e.prev = tail;
                tail = e;
                if (size>=3 && size%2==1){
                    center = center.next;
                }
            }
        }

        void addMaj(long x){
            listElement e = new listElement();
            e.data = x;
            if (size<2){
                add(x);
                return;
            }
            size++;
            listElement tmp = center.next;
            tmp.prev = e;
            e.next = tmp;
            center.next=e;
            e.prev = center;
            if (size % 2 == 1){
                center=center.next;
            }
        }

        long remove() {
            listElement e = head;
            size--;
            if (head.next == null){
                head = null;
                tail = null;
                center = null;
                return e.data;
            }
            head.next.prev = null;
            head = head.next;
            if (size%2==1){
                center = center.next;
            }
            return e.data;
        }

        listElement getHead(){
            return head;
        }


        void print(){
            listElement el = head;
            while (el != null){
                System.out.print(el.data+" ");
                el = el.next;
            }
            System.out.print(" (h: "+(head == null? "null" : head.data)+" m:"+(center==null? "null" : center.data)+ " t:"+(tail==null? "null":tail.data)+")");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n, tmp=0;
        char p;
        n = s.nextInt();
        queue q = new queue();
        for(int i=0; i<n; i++) {
            p = s.next().charAt(0);
            if (p=='+'){
                tmp = s.nextInt();
                q.add(tmp);
            }
            if (p=='*'){
                tmp = s.nextInt();
                q.addMaj(tmp);
            }
            if (p=='-'){
                System.out.println(q.remove());
            }
           // q.print();
        }
    }
}
