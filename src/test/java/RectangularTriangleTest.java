import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class RectangularTriangleTest {

    private RectangularTriangle test1 = new RectangularTriangle(Map.of("a", 3.0, "b", 4.00));
    private RectangularTriangle test2 = new RectangularTriangle(Map.of("a", 8.0, "c", 17.0));
    private RectangularTriangle test3 = new RectangularTriangle(Map.of("a", 5.0, "p", 30.0));
    private RectangularTriangle test4 = new RectangularTriangle(Map.of("b", 12.0, "c", 13.0));
    private RectangularTriangle test5 = new RectangularTriangle(Map.of("b", 15.0, "p", 60.0));
    private RectangularTriangle test6 = new RectangularTriangle(Map.of("c", 13.0, "p", 30.0));

    @Test
    public void getHypotenuse() {
        assertEquals(5.0, test1.getHypotenuse(), 0.001);
        assertEquals(17.0, test2.getHypotenuse(), 0.001);
        assertEquals(13.0, test3.getHypotenuse(), 0.001);
        assertEquals(13.0, test4.getHypotenuse(), 0.001);
        assertEquals(17.0, test5.getHypotenuse(), 0.001);
        assertEquals(13.0, test6.getHypotenuse(), 0.001);

    }

    @Test
    public void getCathetus1() {
        assertEquals(3.0, test1.getCathetus1(), 0.001);
        assertEquals(8.0, test2.getCathetus1(), 0.001);
        assertEquals(5.0, test3.getCathetus1(), 0.001);
        assertEquals(5.0, test4.getCathetus1(), 0.001);
        assertEquals(8.0, test5.getCathetus1(), 0.001);
        assertEquals(5.0, test6.getCathetus1(), 0.001);
    }

    @Test
    public void getCathetus2() {
        assertEquals(4.0, test1.getCathetus2(), 0.001);
        assertEquals(15.0, test2.getCathetus2(), 0.001);
        assertEquals(12.0, test3.getCathetus2(), 0.001);
        assertEquals(12.0, test4.getCathetus2(), 0.001);
        assertEquals(15.0, test5.getCathetus2(), 0.001);
        assertEquals(12.0, test6.getCathetus2(), 0.001);
    }

    @Test
    public void getArea() {
        assertEquals(6.0, test1.getArea(), 0.001);
        assertEquals(60.0, test2.getArea(), 0.001);
        assertEquals(30.0, test3.getArea(), 0.001);
        assertEquals(30.0, test4.getArea(), 0.001);
        assertEquals(60.0, test5.getArea(), 0.001);
        assertEquals(30.0, test6.getArea(), 0.001);
    }

    @Test
    public void getPerimeter() {
        assertEquals(12.0, test1.getPerimeter(), 0.001);
        assertEquals(40.0, test2.getPerimeter(), 0.001);
        assertEquals(30.0, test3.getPerimeter(), 0.001);
        assertEquals(30.0, test4.getPerimeter(), 0.001);
        assertEquals(40.0, test5.getPerimeter(), 0.001);
        assertEquals(30.0, test6.getPerimeter(), 0.001);
    }
}