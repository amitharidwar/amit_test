package java_program;


import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyHashSet<E> {
    private static class Node<E> {
        final E key;
        Node<E> next;

        Node(E key) {
            this.key = key;
        }
    }

    private Node<E>[] buckets;
    private int capacity = 16;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public MyHashSet() {
        buckets = (Node<E>[]) new Node[capacity];
    }

    public boolean add(E key) {
        int index = getIndex(key);
        Node<E> head = buckets[index];

        // Check if key already exists
        boolean exists = Stream.iterate(head, Objects::nonNull, n -> n.next)
                .anyMatch(n -> Objects.equals(n.key, key));

        if (exists) return false;

        // Insert at head
        Node<E> newNode = new Node<>(key);
        newNode.next = head;
        buckets[index] = newNode;
        size++;
        return true;
    }

    public boolean contains(E key) {
        return Stream.iterate(buckets[getIndex(key)], Objects::nonNull, n -> n.next)
                .anyMatch(n -> Objects.equals(n.key, key));
    }

    private int getIndex(E key) {
        return (key == null) ? 0 : Math.abs(key.hashCode()) % capacity;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "[" + Arrays.stream(buckets)
                .filter(Objects::nonNull)
                .flatMap(bucket -> Stream.iterate(bucket, Objects::nonNull, n -> n.next))
                .map(n -> String.valueOf(n.key))
                .collect(Collectors.joining(", "))
                + "]";
    }

    public static void main(String[] args) {
        MyHashSet<String> set = new MyHashSet<>();
        set.add("Apple");
        set.add("Banana");
        set.add("Orange");
        set.add("Banana"); // duplicate ignored

        System.out.println(set.contains("Apple"));  // true
        System.out.println(set.contains("Grapes")); // false
        System.out.println(set);                   // {Orange, Banana, Apple}
    }
}
