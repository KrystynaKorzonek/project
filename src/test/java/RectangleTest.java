import org.junit.Assert;
import org.junit.Test;
import shapes.Rectangle;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RectangleTest {
    @Test
    public void test_all_given_sides() {
        Map<String, Double> features = new HashMap<>();
        features.put("a",3.0);
        features.put("b",5.0);
        Rectangle rectangle = new Rectangle(features);
        double actualArea = rectangle.getArea();
        double expectedArea = 15;
        double actualPerimeter = rectangle.getPerimeter();
        double expectedPerimeter = 16.0;
        double actualDiagonal = rectangle.getDiagonal();
        double expectedDiagonal = 5.830951894845301;
        Assert.assertEquals(expectedArea, actualArea, 0);
        Assert.assertEquals(expectedPerimeter, actualPerimeter, 0);
        Assert.assertEquals(expectedDiagonal, actualDiagonal, 0.0000001);
    }

    @Test
    public void test_all_given_diagonal_and_side() {
        Map<String, Double> features = new HashMap<>();
        features.put("a",3.0);
        features.put("e",5.0);
        Rectangle rectangle = new Rectangle(features);
        double actualArea = rectangle.getArea();
        double expectedArea = 12.0;
        double actualPerimeter = rectangle.getPerimeter();
        double expectedPerimeter = 14.0;
        double actualDiagonal = rectangle.getSide2();
        double expectedDiagonal = 4.0;
        Assert.assertEquals(expectedArea, actualArea, 0);
        Assert.assertEquals(expectedPerimeter, actualPerimeter, 0);
        Assert.assertEquals(expectedDiagonal, actualDiagonal, 0.0000001);
    }
    @Test
    public void test_all_given_diagonal_and_second_side() {
        Map<String, Double> features = new HashMap<>();
        features.put("b",3.0);
        features.put("e",5.0);
        Rectangle rectangle = new Rectangle(features);
        double actualArea = rectangle.getArea();
        double expectedArea = 12.0;
        double actualPerimeter = rectangle.getPerimeter();
        double expectedPerimeter = 14.0;
        double actualSide2 = rectangle.getSide2();
        double expectedSide2 = 4.0;
        Assert.assertEquals(expectedArea, actualArea, 0);
        Assert.assertEquals(expectedPerimeter, actualPerimeter, 0);
        Assert.assertEquals(expectedSide2, actualSide2, 0.0000001);
    }

    @Test
    public void test_all_given_diagonal_and_area() {
        Map<String, Double> features = new HashMap<>();
        features.put("p",12.0);
        features.put("e",5.0);
        Rectangle rectangle = new Rectangle(features);
        double actualSide1 = rectangle.getSide1();
        double expectedSide1 = 3.0;
        double actualPerimeter = rectangle.getPerimeter();
        double expectedPerimeter = 14.0;
        double actualSide2 = rectangle.getSide2();
        double expectedSide2 = 4.0;
        Assert.assertEquals(expectedSide1, actualSide1, 0);
        Assert.assertEquals(expectedPerimeter, actualPerimeter, 0);
        Assert.assertEquals(expectedSide2, actualSide2, 0.0000001);
    }

    @Test
    public void test_all_given_side_and_area() {
        Map<String, Double> features = new HashMap<>();
        features.put("p",12.0);
        features.put("a",3.0);
        Rectangle rectangle = new Rectangle(features);
        double actualSide2 = rectangle.getSide2();
        double expectedSide2 = 4.0;
        double actualPerimeter = rectangle.getPerimeter();
        double expectedPerimeter = 14.0;
        double actualDiagonal = rectangle.getDiagonal();
        double expectedDiagonal = 5.0;
        Assert.assertEquals(expectedSide2, actualSide2, 0);
        Assert.assertEquals(expectedPerimeter, actualPerimeter, 0);
        Assert.assertEquals(expectedDiagonal, actualDiagonal, 0.0000001);
    }

    @Test
    public void test_all_given_second_side_and_area() {
        Map<String, Double> features = new HashMap<>();
        features.put("p",12.0);
        features.put("b",3.0);
        Rectangle rectangle = new Rectangle(features);
        double actualSide2 = rectangle.getSide2();
        double expectedSide2 = 4.0;
        double actualPerimeter = rectangle.getPerimeter();
        double expectedPerimeter = 14.0;
        double actualDiagonal = rectangle.getDiagonal();
        double expectedDiagonal = 5.0;
        Assert.assertEquals(expectedSide2, actualSide2, 0);
        Assert.assertEquals(expectedPerimeter, actualPerimeter, 0);
        Assert.assertEquals(expectedDiagonal, actualDiagonal, 0.0000001);
    }

}
