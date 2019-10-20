List merge(List a, List b) {
    if (a == null) return b;
    if (b == null) return a;

    List c = new List();
    if (a.data > b.data) {
        c = a;
        c.next = merge(a.next, b);
    } else {
        c = b;
        c.next = merge(a, b.next);
    }
    return c;
}

List mergeSort(List head) {
    if (head == null || head.next == null) {
        return head;
    }
    List a = head;
    b = head.next;
    while ((b != null) && (b.next != null)) {
        head = head.next;
        b = b.next.next;
    }
    b = head.next;
    head.next = NULL;
    return merge(mergesort(a), mergesort(b));
}
