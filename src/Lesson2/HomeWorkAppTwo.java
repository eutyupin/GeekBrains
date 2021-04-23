package Lesson2;

import java.util.Scanner;

public class HomeWorkAppTwo {
    public static void main(String[] args) {
        int a = 10;
        int b = 9;
        int c = -1;
        int d = -3;
        String strToPrint = "Hello!!!";
        int countPrint = 5;
        System.out.println(dvachisla(a,b));
        System.out.println(znakchisla(c));
        System.out.println(otriPolo(d));
        printStringCount(strToPrint, countPrint);

       //*****************************************************************
        // определение високосного года
        Scanner vvod = new Scanner(System.in);
        System.out.println("Ведите год для определение високосный ли он:");
        System.out.println(leapYear(vvod.nextInt()));
        //****************************************************************

    }
    public static boolean dvachisla(int a, int b){
        return a + b >= 10 && a + b <= 20;
    }
    public static String znakchisla(int a){
        if (a >= 0) return "Число положительное"; else return "Число отрицательное";
    }
    public static boolean otriPolo(int a){
        return a < 0;
    }
    public static void printStringCount(String str, int a){
        for (int i = 1; i <= a; i++) System.out.println(str);
    }
    public static boolean leapYear(int a){
        return (a % 400 == 0) || (a % 100 != 0 && a % 4 == 0);
    }
}
