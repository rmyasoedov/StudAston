package org.decryptor;

public class Calc {

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
