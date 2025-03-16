public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world: " + factorial(10));
    }

    public static int factorial(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static double valueTriangle(double a, double b, double c) {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public static boolean compare(int a, int b) {
        return a == b;
    }

    private static int add(int a, int b) {
        return a + b;
    }

    private static int subtract(int a, int b) {
        return a - b;
    }

    private static int multiply(int a, int b) {
        return a * b;
    }

    private static String divide(int a, int b) {
        return (b != 0) ? String.valueOf(a / b) : "Ошибка деления на 0";
    }
}