import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        FirstArrTask firstArrTask = new FirstArrTask();
        SecondArrTask secondArrTask = new SecondArrTask();
        int[] firstArr = {1,2,3,4,5,1,6,7,8,9,10};
        int[] secondArr = {2,5,8,9,6,1,8,5,8,5};
        System.out.println(Arrays.toString(firstArrTask.arrCrop(firstArr)));
        System.out.println(secondArrTask.checkArray(secondArr));
    }

}
