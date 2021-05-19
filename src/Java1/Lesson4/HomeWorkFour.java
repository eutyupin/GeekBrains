package Java1.Lesson4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HomeWorkFour {
    private static final int SIZE = 5;
    private static final int WIN_FACTOR = 4; // сколько должно совпасть чтобы победить
    private static final char DOT_EMPTY = '•';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';
    private static char[][] map = new char[SIZE][SIZE];

    public static void main(String[] args) {
        initMap();
        while (true) {
            humanTurn();
            printMap();
            if (isWinner(DOT_X)) {
                System.out.println("Человек победил!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья!");
                break;
            }
            computerTurn();
            printMap();
            if (isWinner(DOT_O)) {
                System.out.println("Компьютер победил!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья!");
                break;
            }
        }
    }
    private static boolean isWinner(char symbol) {
        int check = 0;
        // проверка строк
        for (int i = 0; i < SIZE; i++){
            check = 0;
            for (int j = 0; j < SIZE; j++){
                if(map[i][j] == symbol) check++;
                if (check == WIN_FACTOR) return true;
            }
        }
        // проверка столбцов
        for (int j = 0; j < SIZE; j++){
            check = 0;
            for (int i = 0; i < SIZE; i++){
                if(map[i][j] == symbol) check++;
                if (check == WIN_FACTOR) return true;
            }
        }
        // проверка основных диагоналей
        check = 0;
        for (int i = 0; i < SIZE; i++){
                if(map[i][i] == symbol) check++;
                if (check == WIN_FACTOR) return true;
            }
        // проверка основных диагоналей
        check = 0;
        for (int i = 0; i < SIZE; i++){
            if(map[i][SIZE - i - 1] == symbol) check++;
            if(check == WIN_FACTOR) return  true;
        }
        // проверка малых диагоналей слева направо
        check = 0;
        for (int i = 1; i < SIZE; i++){
            if(map[i][i-1] == symbol) check++;
            if (check == WIN_FACTOR) return true;
        }
        // проверка малых диагоналей слева направо
        check = 0;
        for (int i = 0; i < SIZE - 1; i++){
            if(map[i][i+1] == symbol) check++;
            if (check == WIN_FACTOR) return true;
        }
        // проверка малых диагоналей спарва налево
        check = 0;
        for (int i = 0; i < SIZE - 1; i++) {
            if (map[i][SIZE - i - 2] == symbol) check++;
            if (check == WIN_FACTOR) return true;
        }
        // проверка малых диагоналей спарва налево
        check = 0;
        for (int i = 1; i < SIZE; i++) {
            if (map[i][SIZE - i] == symbol) check++;
            if (check == WIN_FACTOR) return true;
        }
        return  check == WIN_FACTOR;
    }
    private static boolean isMapFull(){
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                if(map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }
    private static int readInt(Scanner scanner){
        return scanner.hasNextInt() ? scanner.nextInt(): -1;
    }
    private static void humanTurn(){
        int posX;
        int posY;
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Введите координаты в формате \"Строка\" \"Столбец\"");
            posX = readInt(scanner) - 1;
            posY = readInt(scanner) - 1;
            if (posX == -2 || posY == -2) {
                System.out.print("Данные введены не корректно!\nКоординаты должны быть числом!\n\n");
                scanner.nextLine();
            } else if (posX < 0 || posX >= SIZE || posY < 0 || posY >= SIZE){
                System.out.print("Данные введены не корректно!\nКоординаты вышли за пределы игрового поля!\n\n");
            } else if(map[posX][posY] != DOT_EMPTY) {
                System.out.println("Клетка уже занята!");
            } else break;
        }
        map[posX][posY] = DOT_X;
    }
    private static void computerTurn(){
        int posX;
        int posY;
        Random random = new Random();
        int [] check = {0,0};
        do{
            check = humanAnalysis();
//            System.out.println(Arrays.toString(check));
            if((check[0] != 0 || check[1] != 0) && (map[check[0]][check[1]] == DOT_EMPTY)) {
                posX = check[0];
                posY = check[1];
            } else {
                posX = random.nextInt(SIZE);
                posY = random.nextInt(SIZE);
            }
        }while (map[posX][posY] != DOT_EMPTY);

        map[posX][posY] = DOT_O;

    }
    private static void initMap() {
        for (int i = 0; i < SIZE; i++){
            Arrays.fill(map[i], DOT_EMPTY);
        }
        printMap();
    }
    private static void printMap() {
        printHeader();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < SIZE; j++) {
                System.out.print("  " + map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    private static void printHeader() {
        System.out.print("   ");
        for (int i = 0; i < SIZE; i++){
            System.out.print(i + 1 + "  ");
        }
        System.out.println();
    }
    private static int[] humanAnalysis() {
        int check = 0;
        int[] targetCell = {0, 0};

        // проверка строк
        for (int i = 0; i < SIZE; i++) {
            check = 0;
            for (int k = 0; k < targetCell.length; k++) targetCell[k] = 0;
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_X) {
                    targetCell[0] = i;
                    if (j == SIZE - 1) targetCell[1] = SIZE - WIN_FACTOR;
                    else targetCell[1] = j + 1;
                    check++;
                }
            }
            if ((check == WIN_FACTOR - 1 || check == WIN_FACTOR - 2) && (map[targetCell[0]][targetCell[1]] != DOT_O)) return targetCell;
        }
        // проверка столбцов
        for (int j = 0; j < SIZE; j++) {
            check = 0;
            for (int k = 0; k < 2; k++) targetCell[k] = 0;
            for (int i = 0; i < SIZE; i++) {
                if (map[i][j] == DOT_X) {
                    targetCell[1] = j;
                    if (i == SIZE - 1) targetCell[0] = SIZE - WIN_FACTOR;
                    else targetCell[0] = i + 1;
                    check++;
                }
            }
            if ((check == WIN_FACTOR - 1 || check == WIN_FACTOR - 2) && (map[targetCell[0]][targetCell[1]] != DOT_O)) return targetCell;
        }
        // проверка основных диагоналей
        check = 0;
        for (int k = 0; k < 2; k++) targetCell[k] = 0;
        for (int i = 0; i < SIZE; i++){
            if(map[i][i] == DOT_X) {
                if(i == SIZE-1) {
                    targetCell[0] = SIZE - WIN_FACTOR;
                    targetCell[1] = SIZE - WIN_FACTOR;
                } else {
                    targetCell[0] = i + 1;
                    targetCell[1] = i + 1;
                }
                check++;
            }
            if ((check == WIN_FACTOR - 1 || check == WIN_FACTOR - 2) && (map[targetCell[0]][targetCell[1]] != DOT_O)) return targetCell;
        }
        // проверка основных диагоналей
        check = 0;
        for (int k = 0; k < 2; k++) targetCell[k] = 0;
        for (int i = 0; i < SIZE; i++){
            if(map[i][SIZE - i - 1] == DOT_X) {
                if(i == SIZE-1) {
                    targetCell[0] = SIZE - WIN_FACTOR;
                    targetCell[1] = SIZE - WIN_FACTOR;
                } else {
                    targetCell[0] = i + 1;
                    targetCell[1] = SIZE - i - 2;
                }
                check++;
            }
            if ((check == WIN_FACTOR - 1 || check == WIN_FACTOR - 2) && (map[targetCell[0]][targetCell[1]] != DOT_O)) return targetCell;
        }
    return targetCell;

    }
}
