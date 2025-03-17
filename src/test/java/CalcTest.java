import org.decryptor.Calc;
import org.decryptor.Comparator;
import org.decryptor.Factorial;
import org.decryptor.Triangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalcTest {


    @Test
    @DisplayName("Тест арифметических операций")
    void testArithmetical() {
        assertAll("arithmetical operations",
                ()-> assertEquals(5, Calc.add(2, 3)),
                ()->assertEquals(5, Calc.subtract(10, 5)),
                ()->assertEquals(50, Calc.multiply(10, 5)),
                ()->assertEquals("2", Calc.divide(10, 5)),
                ()->assertEquals("Ошибка деления на 0", Calc.divide(10, 0))
        );
    }

    @Test
    @DisplayName("Тест вычисления факториала")
    void testFactorial(){
        assertEquals(120, Factorial.invoke(5));
        assertEquals(87178291200L, Factorial.invoke(14));
    }

    @Test
    @DisplayName("Тест нахождения площади треугольника")
    void testValueTriangle(){
        assertEquals(14.7, Triangle.getArea(5,6,7));
        assertEquals(6, Triangle.getArea(3,4,5));
    }

    @Test
    @DisplayName("Тест определения равенства чисел")
    void testCompare(){
        assertEquals("a > b", Comparator.compare(10,5));
        assertEquals("a < b", Comparator.compare(10,50));
        assertEquals("a = b", Comparator.compare(10,10));
    }
}
