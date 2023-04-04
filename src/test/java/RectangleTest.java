import static org.junit.Assert.assertEquals;

public class RectangleTest {
    @org.junit.Test
    public void isFeatureCode() {
        boolean result = Rectangle.isFeatureCode("p");
        boolean correct = true;

        assertEquals(correct, result);


    }
}
