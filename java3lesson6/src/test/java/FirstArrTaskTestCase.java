import org.junit.*;

public class FirstArrTaskTestCase {
    private FirstArrTask firstArrTask;

    @Before
    public void create() {
        firstArrTask = new FirstArrTask();
    }
    @After
    public void release() {
        firstArrTask = null;
    }

    @Test
    public void testarrCrop_1() {
        int[] tmpArr = {1,2,3,4,5,6,7};
        int[] waiting = {5,6,7};
        int[] result = firstArrTask.arrCrop(tmpArr);
        Assert.assertArrayEquals(waiting,result);
    }
    @Test
    public void testarrCrop_2() {
        int[] tmpArr = {1,2,3,4,4,6,7};
        int[] waiting = {6,7};
        int[] result = firstArrTask.arrCrop(tmpArr);
        Assert.assertArrayEquals(waiting,result);
    }

    @Test (expected = RuntimeException.class)
    public void testarrCrop_3() {
        int[] tmpArr = {1,2,3,2,5,6,7};
        int[] waiting = {};
        int[] result = firstArrTask.arrCrop(tmpArr);
        Assert.assertArrayEquals(waiting,result);
    }
}
