class Node<E> {
    private Node<E> next;
    private E data;

    public Node(E data) {
        this.data = data;
    }

    public void appendToTail(E data) {
        Node<E> end = new Node<>(data);

        Node<E> current = this;
        while (current.next != null) {
            current = current.next;
        }

        current.next = end;
    }

    public Node<E> deleteNode(E data) {
        Node<E> head = this;

        if (head.data == data) {
            return head.next;
        }

        Node<E> current = head;

        while (current.next != null) {
            if (current.next.data == data) {
                current.next = current.next.next;
                return head;
            }

            current = current.next;
        }

        return head;
    }

    public E getData() {
        return data;
    }

    public Node<E> getNext() {
        return next;
    }
}
