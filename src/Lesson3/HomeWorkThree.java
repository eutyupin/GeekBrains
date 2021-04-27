package Lesson3;

import java.util.Arrays;

public class HomeWorkThree {
   private static byte[] binArray = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
   private static int[] intArrayHundered = new int[100];
   private static int[] fixedIntArray = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
   private static int[][] squareArray = new int[10][10];
   private static int[] valueArray = {1,2,3,5,8,9,85,42,62,45};
   private static int[] variableArray = {1,2,3,4,7};
   private static int[] arrayForEightTask = {1,2,3,4,5,6};

    public static void main(String[] args) {
        binArrayInvert();
        fillIntArrayHundered();
        multipleArrayItems();
        fillSquareArray();
        System.out.println(Arrays.toString(createMyArray(3,21)));
        min_maxArrayFind();
        System.out.println(oneDimetionalArray(variableArray));
    }
    // 1
    private static void binArrayInvert() {
        System.out.println(Arrays.toString(binArray));
        for (int i = 0; i < binArray.length; i++) {
            if (binArray[i] == 0) binArray[i] = 1;
            else binArray[i] = 0;
        }
        System.out.println(Arrays.toString(binArray));
    }
    // 2
    private static void fillIntArrayHundered() {
        for (int i = 0; i < intArrayHundered.length; i++) {
            intArrayHundered[i] = i + 1;
        }
        System.out.println(Arrays.toString(intArrayHundered));
    }
    // 3
    private static void multipleArrayItems(){
        System.out.println(Arrays.toString(fixedIntArray));
        for (int i = 0; i < fixedIntArray.length; i++){
            if (fixedIntArray[i] < 6) fixedIntArray[i] *= 2;
        }
        System.out.println(Arrays.toString(fixedIntArray));
    }
    // 4
    private static void fillSquareArray(){
        String tmpStr = "";
        for (int i = 0; i < squareArray.length; i++){
            squareArray[i][i] = 1;
            squareArray[i][squareArray.length - i - 1] = 1;
        }
        for(int i = 0; i < squareArray.length; i++){

            for (int j = 0; j < squareArray.length; j++){
                tmpStr += squareArray[j][i] + "  ";
            }
        System.out.println(tmpStr);
        tmpStr = "";
        }
    }
    // 5
    private static int[] createMyArray(int len, int initialValue){
        int[] tempArray = new int[len];
        for (int i = 0; i < len; i++){
            tempArray[i] = initialValue;
        }
        return tempArray;
    }
    // 6
    private static void min_maxArrayFind(){
        int minValue = valueArray[1];
        int maxValue = valueArray[1];

        for (int i = 0; i < valueArray.length; i++){
            if(valueArray[i] > maxValue) maxValue = valueArray[i];
            if(valueArray[i] < minValue) minValue = valueArray[i];
        }
        System.out.println("Maximum value: " + maxValue);
        System.out.println("Minimum value: " + minValue);
    }
    // 7
    private static boolean oneDimetionalArray(int[] receivedArray){
        int totalSumm = 0;
        int leftSumm = 0;
        boolean ret = false;
        for(int i = 0; i < receivedArray.length; i++){
            totalSumm += receivedArray[i];
        }
        for(int i = 0; i < receivedArray.length; i++) {
            leftSumm += receivedArray[i];
            if (leftSumm == totalSumm - leftSumm) {
                ret = true;
                break;
            } else ret = false;
        }
        return ret;
    }
}
