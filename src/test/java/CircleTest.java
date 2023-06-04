import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CircleTest {

    @Test
    public void test_all_given_side() {
        Map<String, Double> features = new HashMap<>();
        features.put("r",5.0);
        Circle circle = new Circle(features);
        double actualArea = circle.getArea();
        double expectedArea = 45;
        double actualPerimeter = circle.getPerimeter();
        double expectedPerimeter = 20.0;
        double actualDiagonal = circle.getDiagonal();
        double expectedDiagonal = 7.071;
        Assert.assertEquals(expectedArea, actualArea, 0);
        Assert.assertEquals(expectedPerimeter, actualPerimeter, 0);
        Assert.assertEquals(expectedDiagonal, actualDiagonal, 0.001);
    }

    @Test
    public void test_all_given_diagonal() {
        Map<String, Double> features = new HashMap<>();
        features.put("d",5.0*Math.sqrt(2));
        Circle circle = new Circle(features);
        double actualArea = circle.getArea();
        double expectedArea = 25.0;
        double actualPerimeter = circle.getPerimeter();
        double expectedPerimeter = 20.0;
        double actualSide = circle.getSide();
        double expectedSide = 5.0;
        Assert.assertEquals(expectedArea, actualArea, 0);
        Assert.assertEquals(expectedPerimeter, actualPerimeter, 0);
        Assert.assertEquals(actualSide, expectedSide, 0.00001);
    }
}