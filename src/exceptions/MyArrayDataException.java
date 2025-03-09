package exceptions;

public class MyArrayDataException extends Exception {
    public MyArrayDataException(int row, int column) {
        super("Ошибка преобразования элемента [" + row +", "+column+"]");
    }
}
