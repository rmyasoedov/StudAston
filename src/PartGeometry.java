import geometry.Circle;
import geometry.Rectangle;
import geometry.Shape;
import geometry.Triangle;

public class PartGeometry {

    public static void invoke(){

        Shape[] shapes = new Shape[]{
                new Circle(15, "красный", "зеленый"),
                new Triangle(5,10,12, "черный", "желтый"),
                new Rectangle(10, 5, "черный", "оранжевый")
        };

        for (Shape shape : shapes) {
            shape.printShapeData();
        }

    }


}
