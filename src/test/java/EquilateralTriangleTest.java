import org.junit.Assert;
import org.junit.Test;
import shapes.EquilateralTriangle;
import shapes.Shape;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class EquilateralTriangleTest {
    @Test
    public void test_all_given_side() {
        Map<String, Double> features = new HashMap<>();
        features.put("a",5.0);
        EquilateralTriangle triangle = new EquilateralTriangle(features);
        Shape doubled = triangle.getDoubleShape();
        Assert.assertEquals(21.650635094610966, doubled.getArea(), 0);
        double actualArea = triangle.getArea();
        double expectedArea = 10.825317547305481;
        double actualPerimeter = triangle.getPerimeter();
        double expectedPerimeter = 15.0;
        double actualHeight = triangle.getHeight();
        double expectedHeight = 4.330127018922193;
        Assert.assertEquals(expectedArea, actualArea, 0);
        Assert.assertEquals(expectedPerimeter, actualPerimeter, 0);
        Assert.assertEquals(expectedHeight, actualHeight, 0.0000001);
    }

    @Test
    public void test_all_given_height() {
        Map<String, Double> features = new HashMap<>();
        features.put("h",5.0);
        EquilateralTriangle triangle = new EquilateralTriangle(features);
        Shape doubled = triangle.getDoubleShape();
        Assert.assertEquals(28.86751345948129, doubled.getArea(), 0);
        double actualArea = triangle.getArea();
        double expectedArea = 14.433756729740645;
        double actualPerimeter = triangle.getPerimeter();
        double expectedPerimeter = 17.320508075688775;
        double actualSide = triangle.getSide();
        double expectedSide = 5.773502691896258;
        Assert.assertEquals(expectedArea, actualArea, 0);
        Assert.assertEquals(expectedPerimeter, actualPerimeter, 0);
        Assert.assertEquals(expectedSide, actualSide, 0.0000001);
    }

    @Test
    public void test_all_given_area() {
        Map<String, Double> features = new HashMap<>();
        features.put("p",12.0);
        EquilateralTriangle triangle = new EquilateralTriangle(features);
        Shape doubled = triangle.getDoubleShape();
        Assert.assertEquals(24.000000000000007, doubled.getArea(), 0);
        double actualHeight = triangle.getHeight();
        double expectedHeight = 4.559014113909556;
        double actualPerimeter = triangle.getPerimeter();
        double expectedPerimeter = 15.79288815542991;
        double actualSide = triangle.getSide();
        double expectedSide = 5.26429605180997;
        Assert.assertEquals(expectedHeight, actualHeight, 0);
        Assert.assertEquals(expectedPerimeter, actualPerimeter, 0);
        Assert.assertEquals(expectedSide, actualSide, 0.0000001);
    }
}
