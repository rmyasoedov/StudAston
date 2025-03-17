import org.decryptor.Calc;
import org.decryptor.Comparator;
import org.decryptor.Factorial;
import org.decryptor.Triangle;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CalcTests {
    @Test(description = "Тест арифметических операций")
    void testArithmetical() {
        assertEquals(5, Calc.add(2, 3));
        assertEquals(5, Calc.subtract(10, 5));
        assertEquals(50, Calc.multiply(10, 5));
        assertEquals("2", Calc.divide(10, 5));
        assertEquals("Ошибка деления на 0", Calc.divide(10, 0));
    }

    @Test(description = "Тест вычисления факториала")
    void testFactorial(){
        assertEquals(120, Factorial.invoke(5));
        assertEquals(87178291200L, Factorial.invoke(14));
    }

    @Test(description = "Тест нахождения площади треугольника")
    void testValueTriangle(){
        assertEquals(14.7, Triangle.getArea(5,6,7));
        assertEquals(6, Triangle.getArea(3,4,5));
    }

    @Test(description = "Тест определения равенства чисел")
    void testCompare(){
        assertEquals("a > b", Comparator.compare(10,5));
        assertEquals("a < b", Comparator.compare(10,50));
        assertEquals("a = b", Comparator.compare(10,10));
    }

}
