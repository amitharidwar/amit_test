import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String[] args) {
        // Example usage of the StreamTest class
        System.out.println("StreamTest is running!");


        List<String> list = List.of("apple", "banana", "cherry", "date");


        Map<Integer, List<String>> groupedByLength = list.stream()
                .collect(Collectors.groupingBy(String::length));

        System.out.println(groupedByLength);
    }
}
