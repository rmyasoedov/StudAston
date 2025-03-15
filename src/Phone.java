public class Phone {

    public static void invoke() {
        PhoneBook book = new PhoneBook();
        book.add("Ivanov", "111222333");
        book.add("Petrov", "111222444");
        book.add("Ivanov", "111222555");

        book.search("Ivanov");
    }
}
