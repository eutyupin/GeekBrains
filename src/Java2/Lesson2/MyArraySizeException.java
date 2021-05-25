package Java2.Lesson2;

public class MyArraySizeException extends RuntimeException{
    public MyArraySizeException(int rowCount, int columnCount) {
        super("размер массива " + rowCount + " X " + columnCount);
    }

}
