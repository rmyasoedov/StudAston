package animals;

public class Dish {

    private static int totalFood;

    public Dish(int totalFood) {
        this.totalFood = totalFood;
    }

    public void addFood(int food){
        totalFood += food;
    }

    public boolean eatFood(int food){
        if(totalFood >= food){
            totalFood -= food;
            return true;
        }
        return false;
    }

    public int getTotalFood() {
        return totalFood;
    }

}
