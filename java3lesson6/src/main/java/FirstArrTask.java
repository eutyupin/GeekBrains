public class FirstArrTask {

    public int[] arrCrop(int[] arr) {
        int lastIndex = -1;
        int[] newArr;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == 4) lastIndex = i;
        }
        if (lastIndex == -1) throw new RuntimeException();
        else {
            newArr = new int[arr.length - (lastIndex+1)];
            System.arraycopy(arr,lastIndex+1,newArr,0,arr.length - (lastIndex+1));
        }
        return newArr;
    }
}
