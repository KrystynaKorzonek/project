import org.junit.Assert;
import org.junit.Test;
import shapes.IsoscelesTriangle;
import shapes.Shape;

import java.util.HashMap;
import java.util.Map;

public class IsoscelesTriangleTest {

    @Test
    public void test_all_given_side_and_base() {
        Map<String, Double> features = new HashMap<>();
        features.put("a",3.0);
        features.put("b",5.0);
        IsoscelesTriangle triangle = new IsoscelesTriangle(features);
        Shape doubled = triangle.getDoubleShape();
        Assert.assertEquals(14.309088021254187, doubled.getArea(), 0);
        double actualArea = triangle.getArea();
        double expectedArea = 7.154544010627092;
        double actualPerimeter = triangle.getPerimeter();
        double expectedPerimeter = 13.0;
        double actualHeight = triangle.getHeight();
        double expectedHeight = 4.769696007084728;
        Assert.assertEquals(expectedArea, actualArea, 0);
        Assert.assertEquals(expectedPerimeter, actualPerimeter, 0);
        Assert.assertEquals(expectedHeight, actualHeight, 0.0000001);
    }

    @Test
    public void test_all_given_side_and_height() {
        Map<String, Double> features = new HashMap<>();
        features.put("h",4.0);
        features.put("b",5.0);
        IsoscelesTriangle triangle = new IsoscelesTriangle(features);
        Shape doubled = triangle.getDoubleShape();
        Assert.assertEquals(24.000000000000004, doubled.getArea(), 0);
        double actualArea = triangle.getArea();
        double expectedArea = 12.0;
        double actualPerimeter = triangle.getPerimeter();
        double expectedPerimeter = 16.0;
        double actualBase = triangle.getBase();
        double expectedBase = 6.0;
        Assert.assertEquals(expectedArea, actualArea, 0);
        Assert.assertEquals(expectedPerimeter, actualPerimeter, 0);
        Assert.assertEquals(expectedBase, actualBase, 0.0000001);
    }

    @Test
    public void test_all_given_base_and_height() {
        Map<String, Double> features = new HashMap<>();
        features.put("h",4.0);
        features.put("a",5.0);
        IsoscelesTriangle triangle = new IsoscelesTriangle(features);
        Shape doubled = triangle.getDoubleShape();
        Assert.assertEquals(20.000000000000004, doubled.getArea(), 0);
        double actualArea = triangle.getArea();
        double expectedArea = 10.0;
        double actualPerimeter = triangle.getPerimeter();
        double expectedPerimeter = 14.433981132056603;
        double actualSide = triangle.getSide();
        double expectedSide = 4.716990566028302;
        Assert.assertEquals(expectedArea, actualArea, 0);
        Assert.assertEquals(expectedPerimeter, actualPerimeter, 0);
        Assert.assertEquals(expectedSide, actualSide, 0.0000001);
    }

    @Test
    public void test_all_given_area_and_height() {
        Map<String, Double> features = new HashMap<>();
        features.put("h",4.0);
        features.put("p",10.0);
        IsoscelesTriangle triangle = new IsoscelesTriangle(features);
        Shape doubled = triangle.getDoubleShape();
        Assert.assertEquals(20.000000000000004, doubled.getArea(), 0);
        double actualBase = triangle.getBase();
        double expectedBase = 5.0;
        double actualPerimeter = triangle.getPerimeter();
        double expectedPerimeter = 14.433981132056603;
        double actualSide = triangle.getSide();
        double expectedSide = 4.716990566028302;
        Assert.assertEquals(expectedBase, actualBase, 0);
        Assert.assertEquals(expectedPerimeter, actualPerimeter, 0);
        Assert.assertEquals(expectedSide, actualSide, 0.0000001);
    }

    @Test
    public void test_all_given_area_and_base() {
        Map<String, Double> features = new HashMap<>();
        features.put("a",5.0);
        features.put("p",10.0);
        IsoscelesTriangle triangle = new IsoscelesTriangle(features);
        Shape doubled = triangle.getDoubleShape();
        Assert.assertEquals(20.000000000000004, doubled.getArea(), 0);
        double actualHeight = triangle.getHeight();
        double expectedHeight = 4.0;
        double actualPerimeter = triangle.getPerimeter();
        double expectedPerimeter = 14.433981132056603;
        double actualSide = triangle.getSide();
        double expectedSide = 4.716990566028302;
        Assert.assertEquals(expectedHeight, actualHeight, 0);
        Assert.assertEquals(expectedPerimeter, actualPerimeter, 0);
        Assert.assertEquals(expectedSide, actualSide, 0.0000001);
    }

    @Test
    public void test_all_given_area_and_side() {
        Map<String, Double> features = new HashMap<>();
        features.put("b",4.716990566028302);
        features.put("p",10.0);
        IsoscelesTriangle triangle = new IsoscelesTriangle(features);
        Shape doubled = triangle.getDoubleShape();
        Assert.assertEquals(20.000000000000004, doubled.getArea(), 0);
        double actualHeight = triangle.getHeight();
        double expectedHeight = 3.9999999999999987;
        double actualPerimeter = triangle.getPerimeter();
        double expectedPerimeter = 14.433981132056605;
        double actualBase = triangle.getBase();
        double expectedBase = 5.0;
        Assert.assertEquals(expectedHeight, actualHeight, 0);
        Assert.assertEquals(expectedPerimeter, actualPerimeter, 0);
        Assert.assertEquals(expectedBase, actualBase, 0.0000001);
    }
}