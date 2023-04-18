import org.junit.Test;

import static org.junit.Assert.*;

public class IsosclesTriangleTest {

    @Test
    public void isFeatureCode() {
        boolean result = IsosclesTriangle.isFeatureCode("r");
        boolean correct = false;

        assertEquals(correct, result);

        result = IsosclesTriangle.isFeatureCode("b");
        correct = true;

        assertEquals(correct, result);

    }
}