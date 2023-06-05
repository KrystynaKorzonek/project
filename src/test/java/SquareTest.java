import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class SquareTest {

    @Test
    public void test_all_given_side() {
        Map<String, Double> features = new HashMap<>();
        features.put("b",5.0);
        Square square = new Square(features);
        double actualArea = square.getArea();
        double expectedArea = 25.0;
        double actualPerimeter = square.getPerimeter();
        double expectedPerimeter = 20.0;
        double actualDiagonal = square.getDiagonal();
        double expectedDiagonal = 7.071;
        Assert.assertEquals(expectedArea, actualArea, 0);
        Assert.assertEquals(expectedPerimeter, actualPerimeter, 0);
        Assert.assertEquals(expectedDiagonal, actualDiagonal, 0.001);
    }

    @Test
    public void test_all_given_diagonal() {
        Map<String, Double> features = new HashMap<>();
        features.put("d",5.0*Math.sqrt(2));
        Square square = new Square(features);
        double actualArea = square.getArea();
        double expectedArea = 25.0;
        double actualPerimeter = square.getPerimeter();
        double expectedPerimeter = 20.0;
        double actualSide = square.getSide();
        double expectedSide = 5.0;
        Assert.assertEquals(expectedArea, actualArea, 0);
        Assert.assertEquals(expectedPerimeter, actualPerimeter, 0);
        Assert.assertEquals(actualSide, expectedSide, 0.0000001);
    }

    @Test
    public void test_all_given_area() {
        Map<String, Double> features = new HashMap<>();
        features.put("p",25.0);
        Square square = new Square(features);
        double actualSide = square.getSide();
        double expectedSide = 5.0;
        double actualPerimeter = square.getPerimeter();
        double expectedPerimeter = 20.0;
        double actualDiagonal = square.getDiagonal();
        double expectedDiagonal = 7.0710678118654755;
        Assert.assertEquals(actualSide, expectedSide, 0);
        Assert.assertEquals(expectedPerimeter, actualPerimeter, 0);
        Assert.assertEquals(expectedDiagonal, actualDiagonal, 0.00000001);
    }
}