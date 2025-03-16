package org.decryptor;

public class Calc {
    public static long factorial(int n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static double valueTriangle(double a, double b, double c) {
        double p = (a + b + c) / 2;
        double value = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        return Math.round(value * 100) / 100.0;
    }

    public static boolean compare(int a, int b) {
        return a == b;
    }

    public static int add(int a, int b) {
        return a + b;
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static String divide(int a, int b) {
        return (b != 0) ? String.valueOf(a / b) : "Ошибка деления на 0";
    }
}
