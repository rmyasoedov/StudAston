import animals.Animal;
import animals.Cat;
import animals.Dog;
import animals.Dish;

public class PartAnimals {

    public static void invoke(){
        Dog dogSharik = new Dog("Шарик");
        dogSharik.run(100);
        dogSharik.swimming(50);

        Cat catBarsik = new Cat("Барсик", 50);
        catBarsik.swimming(20);
        catBarsik.run(10);

        Dish dish = new Dish(300);
        Cat[] cats = createCats();
        feedCats(cats, dish);
        dish.addFood(60);
        feedCats(cats, dish);
        showSatietyCatsStatus(cats);
        System.out.println("В миске осталось еды: "+dish.getTotalFood());

        showTotalAnimals();
    }

    private static Cat[] createCats(){
        return new Cat[]{
                new Cat("Рыжик", 100),
                new Cat("Матвей", 150),
                new Cat("Черныш", 200),
                new Cat("Маркиз", 100)
        };
    }

    private static void feedCats(Cat[] cats, Dish dish){
        for (Cat cat : cats) {
            cat.eat(dish);
        }
    }

    private static void showSatietyCatsStatus(Cat[] cats){
        for (Cat cat : cats) {
            System.out.println(cat.getName() + ": "+cat.getSatiety());
        }
    }

    private static void showTotalAnimals(){
        System.out.println("Всего котов: " + Cat.getCatCount());
        System.out.println("Всего собак: " + Dog.getDogCount());
        System.out.println("Всего животных: " + Animal.getAnimalCount());
    }
}
