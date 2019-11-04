import java.util.*;

public class F2 {
    public static class queue {
        class listElement {
            listElement next;
            pair data;
        }

        private listElement head = null;
        private listElement tail = null;

        void add(pair x) {
            if (head == null) {
                listElement e = new listElement();
                e.data = x;
                head = e;
                tail = e;
            } else {
                listElement e = new listElement();
                e.data = x;
                tail.next = e;
                tail = e;
            }
        }

        pair remove() {
            listElement e = head;
            head = head.next;
            if (head == null) tail = null;
            return e.data;
        }

        listElement getHead(){
            return head;
        }
    }
    public static class stack {
        class listElement {
            listElement next;
            pair data;
        }
        listElement top = null;
        void push(pair x) {
            if (top == null) {
                listElement e = new listElement();
                e.data = x;
                e.next = top;
                top = e;
            } else {
                listElement e = new listElement();
                e.data = x;
                e.next = top;
                top = e;
            }
        }

        pair pop() {
            if (top != null) {
                pair data = top.data;
                top = top.next;
                return data;
            }
            return null;
        }
        listElement getTop(){
            return top;
        }
    }

    static class pair{
        int id;
        int val;
        int old;
        pair(int id, int val, int old){
            this.id = id;
            this.val = val;
            this.old = old;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ArrayList<pair> pa = new ArrayList<>();
        for (int i = 0; i<n;i++){
            pa.add(new pair(0, in.nextInt(), i));
        }

        pa.sort(new Comparator<pair>() {
            @Override
            public int compare(pair o1, pair o2) {
                return o1.val-o2.val;
            }
        });

        for (int i = 0; i < n; i++){
            pa.get(i).id = i;
        }

        pair[] p = new pair[n];

        for (int i = 0; i < n; i++){
            p[pa.get(i).old] = pa.get(i);
        }

        queue q = new queue();

        for (int i = 0; i<n; i++){
            q.add(p[i]);
        }

        ArrayList<String> answ = new ArrayList<>();

        int curr_ind = 0;

        stack st = new stack();
        answ.add("push");
        st.push(q.remove());
        while (q.getHead()!=null){
            if (st.getTop()==null){
                answ.add("push");
                st.push(q.remove());
            }
            while (st.getTop().data.id!=curr_ind && q.getHead()!=null){
                answ.add("push");
                st.push(q.remove());
            }
            if (st.getTop().data.id == curr_ind){
                st.pop();
                answ.add("pop");
                curr_ind++;
            }
        }
        if (st.getTop()!=null) {
            while (st.getTop()!=null) {
                if (st.getTop().data.id == curr_ind) {
                    st.pop();
                    answ.add("pop");
                    curr_ind++;
                } else break;
            }
        }
        if (st.getTop()==null){
            for (String i:answ){
                System.out.println(i);
            }
        } else {
            System.out.println("impossible");
        }

    }
}
