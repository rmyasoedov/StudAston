public class Park {

    private String name;
    private String city;
    private String address;
    private Attraction[] attractions;


    public Park(String name, String city, String address, Attraction[] attractions) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.attractions = attractions;
    }

    public static class Attraction{
        private String name;
        private String workTime;
        private int price;

        public Attraction(String name, String workTime, int price){
            this.name = name;
            this.workTime = workTime;
            this.price = price;
        }
    }
}
