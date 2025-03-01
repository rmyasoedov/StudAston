import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
        System.out.println(isRange10to20(6, 4));
        checkTypeNumber(-9);
        System.out.println(isNegativeNumber(-5));
        repeatString("Hello Aston!", 5);

        int year = 2100;
        System.out.println(year + ": " + isLeapYear(year));

        changeArray(new int[] {1,0,0,1,0,1,1,1,0,1});
        createArray100();
        modifyArray2x();
        createMatrix(5);
        printArray("Массив одинаковых значений: ", getArrayForSize(10, 15));
    }

    public static void printThreeWords() {
        System.out.println("Orange\nBanana\nApple");
    }

    public static void checkSumSign() {
        int a = 25;
        int b = 10;

        System.out.println(
                (a + b >= 0) ? "Сумма положительная" : "Сумма отрицательная"
        );
    }

    public static void printColor() {
        int value = -50;

        String color = (value > 0 && value <= 100) ? "Желтый" : (value>100 ? "Зеленый" : "Красный");
        System.out.println(color);
    }

    public static void compareNumbers() {
        int a = 78;
        int b = 97;

        System.out.println((a >= b) ? "a >= b" : "a < b");
    }

    public static boolean isRange10to20(int firstNumber, int secondNumber) {
        int sum = firstNumber + secondNumber;
        return (sum >= 10 && sum <= 20);
    }

    public static void checkTypeNumber(int number) {
        System.out.println(number + ": " + ((number < 0) ? "отрицательное" : "положительное"));
    }

    public static boolean isNegativeNumber(int number) {
        return number < 0;
    }

    public static void repeatString(String str, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(str);
        }
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static void changeArray(int array[]){
        printArray("Исходный массив: ", array);
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] == 1 ? 0 : 1;
        }
        printArray("Перевернутый массив: ", array);
    }

    public static void createArray100(){
        final int size = 100;
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = i+1;
        }
        printArray("Массив 100: ", array);
    }

    public static void modifyArray2x(){
        int[] array = new int[]{1,5,3,2,11,4,5,2,4,8,9,1};

        for (int i = 0; i < array.length; i++) {
            array[i] = (array[i]<6) ? array[i]*2 : array[i];
        }

        printArray("Массив (2х): ", array);
    }

    public static void createMatrix(int size){
        int[][] matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            matrix[i][i] = 1;
            matrix[i][size - 1 - i] = 1;
        }

        for (int [] row : matrix) {
            printArray("", row);
        }
    }

    public static int[] getArrayForSize(int len, int initialValue){
        int[] array = new int[len];
        Arrays.fill(array, initialValue);
        return array;
    }

    public static void printArray(String beginText, int array[]){
        System.out.print(beginText + Arrays.toString(array)+"\n");
    }
}