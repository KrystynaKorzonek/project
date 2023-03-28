import org.junit.Test;

import static org.junit.Assert.*;

public class SquareTest {

    @Test
    public void isFeatureCode() {
        boolean result = Square.isFeatureCode("r");
        boolean correct = false;

        assertEquals(correct, result);

        result = Square.isFeatureCode("b");
        correct = true;

        assertEquals(correct, result);


    }
}