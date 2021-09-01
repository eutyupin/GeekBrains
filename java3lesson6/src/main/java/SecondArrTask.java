public class SecondArrTask {

    public boolean checkArray(int[] arr) {
        for (int i : arr) {
            if (i == 4 || i == 1) return true;
        }
        return false;
    }
}
