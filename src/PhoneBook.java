import java.util.*;

public class PhoneBook {

    private Map<String, List<String>> book = new HashMap<>();

    public void add(String lastName, String phoneNumber) {
        book.computeIfAbsent(lastName, k -> new ArrayList<>()).add(phoneNumber);
    }

    public List<String> get(String lastName) {
        return book.getOrDefault(lastName, Collections.emptyList());
    }

    public void search(String lastName) {
        var findList = get(lastName);
        System.out.println(findList.isEmpty() ?
                "не найдено номеров с такой фамилией" :
                String.join("\n", findList));

    }

}
