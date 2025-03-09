package animals;

public class Dog extends Animal {

    private static final int MAX_RUNNING_DISTANCE = 500;
    private static final int MAX_SWIMMING_DISTANCE = 10;
    private static int dogCount = 0;

    public Dog(String name) {
        super(name, MAX_RUNNING_DISTANCE, MAX_SWIMMING_DISTANCE);
        dogCount++;
    }

    public static int getDogCount() {
        return dogCount;
    }
}
