package Java2.Lesson2;

public class MyArrayDataException extends RuntimeException{
    MyArrayDataException(int index, String s) {
        super(String.format("В ячейке №" + index + " не число, а символ \"" + s + "\""));
    }
}
