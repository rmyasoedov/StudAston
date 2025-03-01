public class Product {
    private String name;
    private String manufacturer;
    private String createdDate;
    private String country;
    private int price;
    private boolean bookingStatus;

    public Product(String name, String manufacturer, String createdDate, String country, int price, boolean bookingStatus) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.createdDate = createdDate;
        this.country = country;
        this.price = price;
        this.bookingStatus = bookingStatus;
    }

    public void printDataProduct(){
        System.out.println("Наименование: " + name);
        System.out.println("Производитель: " + manufacturer);
        System.out.println("Дата производства: " + createdDate);
        System.out.println("Страна происхождения: " + country);
        System.out.println("Цена: " + price + "$");
        System.out.println("Статус бронирования: " + (bookingStatus ? "да" : "нет"));
    }
}
