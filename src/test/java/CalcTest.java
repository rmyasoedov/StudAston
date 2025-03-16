import org.decryptor.Calc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalcTest {


    @Test
    @DisplayName("Тест нахождения суммы")
    void testAddition() {
        assertEquals(5, Calc.add(2, 3));
    }

    @Test
    @DisplayName("Тест нахождения разности")
    void testSubtraction() {
        assertEquals(5, Calc.subtract(10, 5));
    }

    @Test
    @DisplayName("Тест нахождения произведения")
    void testMultiplication() {
        assertEquals(50, Calc.multiply(10, 5));
    }

    @Test
    @DisplayName("Тест нахождения деления")
    void testDivision() {
        assertEquals("2", Calc.divide(10, 5));
        assertEquals("Ошибка деления на 0", Calc.divide(10, 0));
    }

    @Test
    @DisplayName("Тест вычисления факториала")
    void testFactorial(){
        assertEquals(120, Calc.factorial(5));
        assertEquals(87178291200L, Calc.factorial(14));
    }

    @Test
    @DisplayName("Тест нахождения площади треугольника")
    void testValueTriangle(){
        assertEquals(14.7, Calc.valueTriangle(5,6,7));
        assertEquals(6, Calc.valueTriangle(3,4,5));
    }

    @Test
    @DisplayName("Тест определения равенства чисел")
    void testCompare(){
        Assertions.assertTrue(Calc.compare(10,10));
        Assertions.assertFalse(Calc.compare(1,10));
    }
}
