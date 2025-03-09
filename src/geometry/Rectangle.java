package geometry;

public class Rectangle extends Shape {

    private int a, b;

    public Rectangle(int a, int b, String borderColor, String fillColor) {
        super(borderColor, fillColor);
        this.a = a;
        this.b = b;
    }

    @Override
    public double area() {
        return a*b;
    }

    @Override
    public double perimeter() {
        return super.calcPerimeter(a,b,a,b);
    }
}
