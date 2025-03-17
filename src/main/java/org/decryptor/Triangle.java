package org.decryptor;

public class Triangle {
    public static double getArea(double a, double b, double c) {
        double p = (a + b + c) / 2;
        double value = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        return Math.round(value * 100) / 100.0;
    }
}
