package geometry;

public interface Figure {
    default double calcPerimeter(double ...sides){
        double perimeter = 0;
        for(double side : sides){
            perimeter += side;
        }
        return perimeter;
    }

    double area();
    double perimeter();
}
