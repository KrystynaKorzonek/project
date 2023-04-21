import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RectangleTest {
    @org.junit.Test
    public void isFeatureCode() {
        boolean result = Rectangle.isFeatureCode("p");
        boolean correct = true;

        assertEquals(correct, result);


    }
    @org.junit.Test
    public void diagonalAreaTest(){
        Rectangle r = new Rectangle(Map.of("e", 13.0, "p", 60.0));
        assertEquals(34.0, r.getPerimeter(), 0.001);
    }

}
