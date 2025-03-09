package geometry;

public abstract class Shape implements Figure  {

    protected String borderColor;
    protected String fillColor;

    public Shape(String borderColor, String fillColor) {
        this.borderColor = borderColor;
        this.fillColor = fillColor;
    }

    public void printShapeData(){
        System.out.println("\nПлощадь: " + area());
        System.out.println("Периметр: " + perimeter());
        System.out.println("Цвет границы: " + borderColor);
        System.out.println("Цвет заливки: " + fillColor);
    }
}
