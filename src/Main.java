public class Main {
    public static void main(String[] args) {
        Product product = new Product("Apple Macbook Air 13", "Apple", "01.05.2022", "USA", 1099, true);
        product.printDataProduct();

        Product[] productsArray = new Product[5];
        productsArray[0] = new Product("Asus ROG Strix G16", "Asus", "12.03.2023", "Taiwan", 1399, true);
        productsArray[1] = new Product("Samsung Galaxy S24", "Samsung", "15.02.2024", "South Korea", 899, false);
        productsArray[2] = new Product("Sony PlayStation 5", "Sony", "12.11.2020", "Japan", 499, true);
        productsArray[3] = new Product("Dell XPS 15", "Dell", "20.08.2023", "USA", 1499, false);
        productsArray[4] = new Product("Xiaomi Mi Band 8", "Xiaomi", "10.04.2023", "China", 49, true);

        Park park = new Park(
                "Summer park",
                "Moscow",
                "Dimitrova str.",
                new Park.Attraction[]{
                        new Park.Attraction("Roller coaster", "11:00 - 16:00", 10),
                        new Park.Attraction("Ferris wheel", "10:00 - 20:00", 8),
                        new Park.Attraction("Haunted house", "12:00 - 22:00", 12)
                }
        );
    }
}