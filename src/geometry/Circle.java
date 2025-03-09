package geometry;

public class Circle extends Shape{

    private int radius;

    public Circle(int radius, String borderColor, String fillColor){
        super(borderColor, fillColor);
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }
}
