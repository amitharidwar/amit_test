package java_program.solid;
public class CustomLinkedList<T> {

    // Node class
    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head;
    private int size;

    public CustomLinkedList() {
        head = null;
        size = 0;
    }

    // Insert at beginning
    public void insertAtBeg(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    // Insert at end
    public void insertAtEnd(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // Insert at given position (0-based index)
    public void insertAtMid(T data, int position) {
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
        if (position == 0) {
            insertAtBeg(data);
            return;
        }
        if (position == size) {
            insertAtEnd(data);
            return;
        }

        Node<T> newNode = new Node<>(data);
        Node<T> current = head;
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    // Delete from beginning
    public void deleteAtBeg() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        head = head.next;
        size--;
    }

    // Delete from end
    public void deleteAtEnd() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        if (head.next == null) {
            head = null;
        } else {
            Node<T> current = head;
            while (current.next.next != null) {
                current = current.next;
            }
            current.next = null;
        }
        size--;
    }

    // Delete from given position
    public void deleteAtMid(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
        if (position == 0) {
            deleteAtBeg();
            return;
        }
        if (position == size - 1) {
            deleteAtEnd();
            return;
        }
        Node<T> current = head;
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        size--;
    }

    // Display list
    public void display() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Get size of list
    public int getSize() {
        return size;
    }

    // Main method for testing
    public static void main(String[] args) {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();

        list.insertAtBeg(3);
        list.insertAtBeg(2);
        list.insertAtEnd(4);
        list.insertAtMid(5, 2);

        list.display(); // 2 -> 3 -> 5 -> 4 -> null

        list.deleteAtBeg();
        list.display(); // 3 -> 5 -> 4 -> null

        list.deleteAtMid(1);
        list.display(); // 3 -> 4 -> null

        list.deleteAtEnd();
        list.display(); // 3 -> null
    }
}

