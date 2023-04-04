import static org.junit.Assert.assertEquals;

public class RhombusTest {
    @org.junit.Test
    public void isFeatureCode() {
        boolean result = Rhombus.isFeatureCode("d");
        boolean correct = true;

        assertEquals(correct, result);


    }
}
