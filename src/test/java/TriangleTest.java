import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TriangleTest {
    Triangle test1 = new Triangle(6.0, 7.0, 8.0);

    @Test
    public void getArea(){
        assertEquals(20.33316256758894, test1.getArea(), 0.0000001);
    }
    @Test
    public void getDoubled(){
        Shape doubled = test1.getDoubleShape();
        Assert.assertEquals(40.66632513517786, doubled.getArea(), 0);
    }
}
