package java_program;


import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyHashMap<K, V> {

    private static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry<K, V>[] buckets;
    private int capacity = 16; // default
    private int size = 0;


    public MyHashMap() {
        buckets = (Entry<K, V>[]) new Entry[capacity];
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        Entry<K, V> head = buckets[index];

        // Find existing key using Stream
        Entry<K, V> existing = entriesStream(head)
                .filter(e -> Objects.equals(e.key, key))
                .findFirst()
                .orElse(null);

        if (existing != null) {
            existing.value = value; // update value
            return;
        }

        // Insert new entry at head
        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = head;
        buckets[index] = newEntry;
        size++;
    }

    public V get(K key) {
        return entriesStream(buckets[getIndex(key)])
                .filter(e -> Objects.equals(e.key, key))
                .map(e -> e.value)
                .findFirst()
                .orElse(null);
    }

    private int getIndex(K key) {
        return (key == null) ? 0 : Math.abs(key.hashCode()) % capacity;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "[" + Arrays.stream(buckets)
                .filter(Objects::nonNull)
                .flatMap(this::entriesStream)
                .map(e -> e.key + "=" + e.value)
                .collect(Collectors.joining(", "))
                + "]";
    }

    // Converts a linked list into a Stream of entries
    private Stream<Entry<K, V>> entriesStream(Entry<K, V> entry) {
        return Stream.iterate(entry, Objects::nonNull, e -> e.next);
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("Apple", 10);
        map.put("Banana", 20);
        map.put("Orange", 30);
        map.put("Banana", 25); // update

        System.out.println(map.get("Apple"));  // 10
        System.out.println(map.get("Banana")); // 25
        System.out.println(map.get("Grapes")); // null
        System.out.println(map);               // {Orange=30, Banana=25, Apple=10}
    }
}
