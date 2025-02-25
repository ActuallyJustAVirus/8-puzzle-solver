public class CappedPriorityLinkedList {
    Node head;
    Node tail;
    int size;
    int cap = 1000;
    int removed = -1;

    public CappedPriorityLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(Board board) {
        Node node = new Node(board);
        if (node.value >= removed && removed != -1) {
            return;
        }
        if (size >= cap && tail != null && node.value >= tail.value) {
            removed = Math.min(removed, node.value);
            return;
        }
        if (tail == null) {
            System.out.println("Warning: Tail is null");
        }
        if (head == null) {
            head = node;
            tail = node;
        } else {
            Node current = head;
            while (current != null) {
                if (node.value < current.value) {
                    if (current == head) {
                        node.next = head;
                        head.prev = node;
                        head = node;
                    } else {
                        node.next = current;
                        node.prev = current.prev;
                        current.prev.next = node;
                        current.prev = node;
                    }
                    break;
                }
                current = current.next;
            }
            if (current == null) {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
        }
        size++;
        if (size > cap) {
            removed = Math.min(removed, tail.value);
            tail = tail.prev;
            tail.next = null;
            size--;
        }
    }

    public Board poll() {
        if (head == null) {
            return null;
        }
        Node node = head;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
        size--;
        if (node.value >= removed && removed != -1) {
            System.out.println("Warning: Polling higher value than removed" + node.value + " " + removed);
        }
        return node.board;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}


class Node {
    int value;
    Board board;
    Node next;
    Node prev;

    public Node(Board board) {
        this.board = board;
        this.value = board.value();
    }
}