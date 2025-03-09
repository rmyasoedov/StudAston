import exceptions.MyArrayDataException;
import exceptions.MyArraySizeExceptions;

public class Main {
    private static final int NEED_ROWS = 4;
    private static final int NEED_COLUMNS = 4;

    public static void main(String[] args) {
        String[][] arrayStr = {
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"}
        };

        try {
            System.out.println("Сумма: "+getSumArray(arrayStr));
        } catch (MyArraySizeExceptions e) {
            System.out.println(e.getMessage());
        }catch (MyArrayDataException e){
            System.out.println(e.getMessage());
        }

        try{
            int row = 6;
            int column = 1;
            arrayStr[row][column] = "5";
            System.out.println("["+(row + 1)+","+(column+1)+"] = "+arrayStr[row][column]);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
    }

    private static int getSumArray(String[][] array) throws MyArraySizeExceptions, MyArrayDataException {
        if (array.length != NEED_ROWS) {
            throw new MyArraySizeExceptions();
        }

        int sum = 0;
        for(int i = 0; i < NEED_ROWS; i++) {
            if (array[i].length != NEED_COLUMNS) {
                throw new MyArraySizeExceptions();
            }

            for (int j = 0; j < NEED_COLUMNS; j++) {
                sum+=strToIntElement(array[i][j], i+1, j+1);
            }
        }
        return sum;
    }

    private static int strToIntElement(String str, int row, int column) throws MyArrayDataException {
        try {
            return Integer.parseInt(str);
        }catch (NumberFormatException e){
            throw new MyArrayDataException(row, column);
        }
    }
}
