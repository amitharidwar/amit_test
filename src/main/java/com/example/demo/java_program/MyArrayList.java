package java_program;


import java.util.Arrays;

public class MyArrayList<E> {
    private Object[] elements = new Object[10];
    private int size = 0;

    public void add(E element) {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
        elements[size++] = element;
    }

    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(index);
        return (E) elements[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elements, size));
    }

    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Hello");
        list.add("World");
        list.add("Java");

        System.out.println("ArrayList: " + list);
        System.out.println("Element at index 1: " + list.get(1));
    }
}
