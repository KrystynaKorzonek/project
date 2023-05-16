import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class EquilateralTrapezoidTest {

    EquilateralTrapezoid test1 = new EquilateralTrapezoid(Map.of("a" , 10.0, "b", 4.0, "c", 5.0));
    EquilateralTrapezoid test2 = new EquilateralTrapezoid(Map.of("a" , 10.0, "b", 4.0, "h", 4.0));
    EquilateralTrapezoid test3 = new EquilateralTrapezoid(Map.of("a" , 10.0, "b", 4.0, "p", 28.0));
    EquilateralTrapezoid test4 = new EquilateralTrapezoid(Map.of("a" , 10.0, "c", 5.0, "h", 4.0));
    EquilateralTrapezoid test5 = new EquilateralTrapezoid(Map.of("a" , 10.0, "h", 4.0, "p", 28.0));
    EquilateralTrapezoid test6 = new EquilateralTrapezoid(Map.of("b" , 4.0, "c", 5.0, "h", 4.0));
    EquilateralTrapezoid test7 = new EquilateralTrapezoid(Map.of("b" , 4.0, "h", 4.0, "p", 28.0));
    EquilateralTrapezoid test8 = new EquilateralTrapezoid(Map.of("c" , 5.0, "h", 4.0, "p", 28.0));

    @Test
    public void getLagerBase() {
        assertEquals(10.0, test1.getLagerBase(), 0.001);
        assertEquals(10.0, test2.getLagerBase(), 0.001);
        assertEquals(10.0, test3.getLagerBase(), 0.001);
        assertEquals(10.0, test4.getLagerBase(), 0.001);
        assertEquals(10.0, test5.getLagerBase(), 0.001);
        assertEquals(10.0, test6.getLagerBase(), 0.001);
        assertEquals(10.0, test7.getLagerBase(), 0.001);
        assertEquals(10.0, test8.getLagerBase(), 0.001);
    }

    @Test
    public void getShorterBase() {
        assertEquals(4.0, test1.getShorterBase(), 0.001);
        assertEquals(4.0, test2.getShorterBase(), 0.001);
        assertEquals(4.0, test3.getShorterBase(), 0.001);
        assertEquals(4.0, test4.getShorterBase(), 0.001);
        assertEquals(4.0, test5.getShorterBase(), 0.001);
        assertEquals(4.0, test6.getShorterBase(), 0.001);
        assertEquals(4.0, test7.getShorterBase(), 0.001);
        assertEquals(4.0, test8.getShorterBase(), 0.001);
    }

    @Test
    public void getLeg() {
        assertEquals(5.0, test1.getLeg(), 0.001);
        assertEquals(5.0, test2.getLeg(), 0.001);
        assertEquals(5.0, test3.getLeg(), 0.001);
        assertEquals(5.0, test4.getLeg(), 0.001);
        assertEquals(5.0, test5.getLeg(), 0.001);
        assertEquals(5.0, test6.getLeg(), 0.001);
        assertEquals(5.0, test7.getLeg(), 0.001);
        assertEquals(5.0, test8.getLeg(), 0.001);
    }

    @Test
    public void getHeight() {
        assertEquals(4.0, test1.getHeight(), 0.001);
        assertEquals(4.0, test2.getHeight(), 0.001);
        assertEquals(4.0, test3.getHeight(), 0.001);
        assertEquals(4.0, test4.getHeight(), 0.001);
        assertEquals(4.0, test5.getHeight(), 0.001);
        assertEquals(4.0, test6.getHeight(), 0.001);
        assertEquals(4.0, test7.getHeight(), 0.001);
        assertEquals(4.0, test8.getHeight(), 0.001);
    }

    @Test
    public void getArea(){
        assertEquals(28.0, test1.getArea(), 0.001);
        assertEquals(28.0, test2.getArea(), 0.001);
        assertEquals(28.0, test3.getArea(), 0.001);
        assertEquals(28.0, test4.getArea(), 0.001);
        assertEquals(28.0, test5.getArea(), 0.001);
        assertEquals(28.0, test6.getArea(), 0.001);
        assertEquals(28.0, test7.getArea(), 0.001);
        assertEquals(28.0, test8.getArea(), 0.001);
    }
}