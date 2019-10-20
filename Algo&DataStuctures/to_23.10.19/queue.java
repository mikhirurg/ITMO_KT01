public class queue {
    static class listElement {
        listElement next;
        String data;
    }

    private listElement head = null;
    private listElement tail = null;

    void add(String x) {
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

    String remove() {
        listElement e = head;
        head = head.next;
        if (head == null) tail = null;
        return e.data;
    }

    listElement getHead(){
        return head;
    }
}
