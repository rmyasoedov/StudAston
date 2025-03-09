package animals;

public class Cat extends Animal {

    private static final int MAX_RUNNING_DISTANCE = 200;
    private static final int MAX_SWIMMING_DISTANCE = 0;
    private static int catCount = 0;
    private boolean isSatiety = false;
    private int levelSatiety;

    public Cat(String name, int levelSatiety) {
        super(name, MAX_RUNNING_DISTANCE, MAX_SWIMMING_DISTANCE);
        this.levelSatiety = levelSatiety;
        catCount++;
    }

    public void eat(Dish dish){
        isSatiety = isSatiety || dish.eatFood(levelSatiety);
    }

    public String getSatiety(){
        return isSatiety ? "сыт" : "голоден";
    }

    public static int getCatCount() {
        return catCount;
    }
}
