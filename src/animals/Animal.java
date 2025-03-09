package animals;

public class Animal {

    protected String name;
    private int maxDistanceRun;
    private int maxDistanceSwimming;
    private static int animalCount = 0;

    public Animal(String name, int maxDistanceRun, int maxDistanceSwimming) {
        this.name = name;
        this.maxDistanceRun = maxDistanceRun;
        this.maxDistanceSwimming = maxDistanceSwimming;
        animalCount++;
    }

    public void run(int distance){
        if(maxDistanceRun==0){
            System.out.println(name+" не умеет бегать");
            return;
        }
        if(distance > maxDistanceRun){
            System.out.println(name+" не может пробежать "+distance+" м.");
        }else{
            System.out.println(name+ " пробежал " + distance+ "м.");
        }
    }

    public void swimming(int distance){
        if(maxDistanceSwimming==0){
            System.out.println(name+" не умеет плавать");
            return;
        }
        if (distance > maxDistanceSwimming) {
            System.out.println(name + " не может проплыть "+distance+" м.");
        }else{
            System.out.println(name+ " проплыл " + distance+"м.");
        }
    }

    public String getName(){
        return name;
    }

    public static int getAnimalCount() {
        return animalCount;
    }
}
