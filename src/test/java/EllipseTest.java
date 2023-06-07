import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class EllipseTest {
    private Ellipse test1 = new Ellipse(Map.of("a", 3.0, "b", 4.0));
    private Ellipse test2 = new Ellipse(Map.of("b", 8.0, "p", 17.0));
    private Ellipse test3 = new Ellipse(Map.of("a", 2.0, "p", 30.0));
    @Test
    public void getSemiMajorAxis() {
        assertEquals(4.0, test1.getSemiMajorAxis(), 0.0000001);
        assertEquals(8.0, test2.getSemiMajorAxis(), 0.0000001);
        assertEquals(4.7746482927568605, test3.getSemiMajorAxis(), 0.0000001);
    }

    @Test
    public void getSemiMinorAxis() {
        assertEquals(3.0, test1.getSemiMinorAxis(), 0.0000001);
        assertEquals(0.6764085081405552, test2.getSemiMinorAxis(), 0.0000001);
        assertEquals(2.0, test3.getSemiMinorAxis(), 0.0000001);
    }

    @Test
    public void getArea() {
        assertEquals(37.69911184307752, test1.getArea(), 0.0000001);
        assertEquals(17.0, test2.getArea(), 0.0000001);
        assertEquals(30.0, test3.getArea(), 0.0000001);
    }
}