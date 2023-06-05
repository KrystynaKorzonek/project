import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CircleTest {

    @Test
    public void test_all_given_radius() {
        Map<String, Double> features = new HashMap<>();
        features.put("r",5.0);
        Circle circle = new Circle(features);
        double actualArea = circle.getArea();
        double expectedArea = 78.53981633974483;
        double actualPerimeter = circle.getPerimeter();
        double expectedPerimeter = 31.41592653589793;
        double actualDiameter = circle.getDiameter();
        double expectedDiameter = 10.0;
        Assert.assertEquals(expectedArea, actualArea, 0);
        Assert.assertEquals(expectedPerimeter, actualPerimeter, 0);
        Assert.assertEquals(actualDiameter, expectedDiameter, 0.0000001);
    }

    @Test
    public void test_all_given_diameter() {
        Map<String, Double> features = new HashMap<>();
        features.put("d", 10.0);
        Circle circle = new Circle(features);
        double actualArea = circle.getArea();
        double expectedArea = 78.53981633974483;
        double actualPerimeter = circle.getPerimeter();
        double expectedPerimeter = 31.41592653589793;
        double actualRadius = circle.getRadius();
        double expectedRadius = 5.0;
        Assert.assertEquals(expectedArea, actualArea, 0);
        Assert.assertEquals(expectedPerimeter, actualPerimeter, 0);
        Assert.assertEquals(actualRadius, expectedRadius, 0.0000001);
    }

    @Test
    public void test_all_given_area() {
        Map<String, Double> features = new HashMap<>();
        features.put("p", 78.53981633974483);
        Circle circle = new Circle(features);
        double actualDiameter = circle.getDiameter();
        double expectedDiameter = 10.0;
        double actualPerimeter = circle.getPerimeter();
        double expectedPerimeter = 31.41592653589793;
        double actualRadius = circle.getRadius();
        double expectedRadius = 5.0;
        Assert.assertEquals(actualDiameter, expectedDiameter, 0);
        Assert.assertEquals(expectedPerimeter, actualPerimeter, 0);
        Assert.assertEquals(actualRadius, expectedRadius, 0.0000001);
    }

    @Test
    public void test_all_given_perimeter() {
        Map<String, Double> features = new HashMap<>();
        features.put("l", 31.41592653589793);
        Circle circle = new Circle(features);
        double actualDiameter = circle.getDiameter();
        double expectedDiameter = 10.0;
        double actualArea = circle.getArea();
        double expectedArea = 78.53981633974483;
        double actualRadius = circle.getRadius();
        double expectedRadius = 5.0;
        Assert.assertEquals(actualDiameter, expectedDiameter, 0);
        Assert.assertEquals(actualArea, expectedArea, 0);
        Assert.assertEquals(actualRadius, expectedRadius, 0.0000001);
    }
}