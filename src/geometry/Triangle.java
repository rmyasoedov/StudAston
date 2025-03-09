package geometry;

public class Triangle extends Shape {
    private int a, b, c;

    public Triangle(int a, int b, int c, String borderColor, String fillColor) {
        super(borderColor, fillColor);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double area() {
        double p = perimeter() / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    public double perimeter() {
        return super.calcPerimeter(a, b, c);
    }
}
