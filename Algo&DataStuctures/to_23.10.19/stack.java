public class stack {
    static class listElement {
        listElement next;
        String data;
    }
    listElement top = null;
    void push(String x) {
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

    String pop() {
        if (top != null) {
            String data = top.data;
            top = top.next;
            return data;
        }
        return null;
    }
    listElement getTop(){
        return top;
    }
}
