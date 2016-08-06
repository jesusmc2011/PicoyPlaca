import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by jesus on 06/08/16.
 */
public class TestPicoyPlaca {

    private PicoyPlaca pp = new PicoyPlaca("MCB-0123", "2016-08-05", "10:30");

    @Test
    public void testGetLastNumber(){
        assertEquals(3, pp.getLastNumber());
    }

    @Test
    public void testGetDayOfWeek(){
        assertEquals(5, pp.getDayOfWeek());
    }

    @Test
    public void testApplyByTime(){
        assertEquals(false, pp.applyByTime());
    }

    @Test
    public void testApplyByDate(){
        assertEquals(false, pp.applyByDate());
    }

    @Test
    public void testApply(){
        assertEquals(false, pp.apply());
    }

}
