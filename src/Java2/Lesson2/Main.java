package Java2.Lesson2;

public class Main {

    private static String[][] stringArray = {{"1","2","3","4"},{"5","6","7", "8"},{"9","10","11","12"},{"13","14","15","16"}};

    public static void main(String[] args) {
        try {
            arrrayReceive(stringArray);
        } catch (MyArraySizeException e) {
            System.out.println("Массив не имеет размер 4 Х 4, а имеет " + e.getMessage());
        }
        try {
            System.out.println("Сумма всех элементов массива составляет: " + arrayDataCheck(stringArray));
        } catch (MyArrayDataException e) {
            System.out.println("Невозможно преобразовать символ к числу! " + e.getMessage());
        }
    }

    private static void arrrayReceive(String[][] arr) {
        int rowCount = 0;
        int columnCount = 0;
        for (String[] row : arr) {
            rowCount++;
            for (String item : row) {
                columnCount++;
            }
        }
        columnCount = columnCount/rowCount;
        if (rowCount != 4 || columnCount != 4) throw new MyArraySizeException(rowCount, columnCount);
        else System.out.println("Все ОК размер массива 4 Х 4");
    }

    private static int arrayDataCheck(String[][] arr) {
        int itemIndex = 0;
        int sumArray = 0;
        for (String[] row : arr) {
            for (String item : row) {
                itemIndex++;
                try {
                    sumArray += Integer.parseInt(item);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(itemIndex,item);
                }
            }
        }
        return sumArray;

    }
}
