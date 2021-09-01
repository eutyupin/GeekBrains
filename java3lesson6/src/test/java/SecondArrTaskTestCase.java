import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SecondArrTaskTestCase {
    private  SecondArrTask secondArrTask;
    @Before
    public void create() {
        secondArrTask = new SecondArrTask();
    }
    @After
    public void release() {
        secondArrTask = null;
    }

    @Test
    public void checkArr_1() {
        int[] tmpArr = {1,2,3,4,5,6,7};
        boolean result = secondArrTask.checkArray(tmpArr);
        Assert.assertTrue(result);
    }

    @Test
    public void checkArr_2() {
        int[] tmpArr = {2,2,3,2,5,6,7};
        boolean result = secondArrTask.checkArray(tmpArr);
        Assert.assertFalse(result);
    }
    @Test
    public void checkArr_3() {
        int[] tmpArr = {4,2,3,1,5,6,0};
        boolean result = secondArrTask.checkArray(tmpArr);
        Assert.assertTrue(result);
    }
}
