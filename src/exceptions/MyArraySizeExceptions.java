package exceptions;

public class MyArraySizeExceptions extends Exception {

    public MyArraySizeExceptions() {
        super("Массив не соответсвует условиям размерности");
    }
}
