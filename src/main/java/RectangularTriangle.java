import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
public class RectangularTriangle extends Shape{
    private double hypotenuse;
    private double cathetus1;
    private double cathetus2;


    private double findAlpha(double value){
        double left = 0;
        double right = Math.PI/2;
        double middle = (left+right)/2;
        while (Math.abs(Math.sin(middle)-value)>0.0000000000001){
            if (Math.sin(middle)>value)
                right = middle;
            else
                left = middle;
            middle = (left+right)/2;
        }
        return middle;
    }

    public RectangularTriangle(Map<String, Double> features) {
        this.dateTime = LocalDateTime.now();
        this.verticesNumber = 3;
        if (features.size() != 2)
            throw new IllegalArgumentException("Too many features\n(that should never happen...)");
        Set<String> codes = new TreeSet<>(features.keySet());
        //if (!isProperSetOfFeatures(codes))
          //  throw new IllegalArgumentException("Wrong features\n(that should never happen...)");
        if (codes.contains("a") && codes.contains("b")) {
            cathetus1 = features.get("a");
            cathetus2 = features.get("b");
            hypotenuse = Math.sqrt(cathetus1 * cathetus1 + cathetus2 * cathetus2);
            area = cathetus1 * cathetus2 / 2;
            perimeter = cathetus1 + cathetus2 + hypotenuse;
        } else if (codes.contains("a") && codes.contains("c")) {
            cathetus1 = features.get("a");
            hypotenuse = features.get("c");
            cathetus2 = Math.sqrt(hypotenuse * hypotenuse - cathetus1 * cathetus1);
            area = cathetus1 * cathetus2 / 2;
            perimeter = cathetus1 + cathetus2 + hypotenuse;
        } else if (codes.contains("a") && codes.contains("p")) {
            cathetus1 = features.get("a");
            area = features.get("p");
            cathetus2 = 2 * area / cathetus1;
            hypotenuse = Math.sqrt(cathetus1 * cathetus1 + cathetus2 * cathetus2);
            perimeter = cathetus1 + cathetus2 + hypotenuse;
        } else if (codes.contains("b") && codes.contains("c")) {
            cathetus2 = features.get("b");
            hypotenuse = features.get("c");
            cathetus1 = Math.sqrt(hypotenuse * hypotenuse - cathetus2 * cathetus2);
            area = cathetus1 * cathetus2 / 2;
            perimeter = cathetus1 + cathetus2 + hypotenuse;
        } else if (codes.contains("b") && codes.contains("p")) {
            cathetus2 = features.get("b");
            area = features.get("p");
            cathetus1 = 2 * area / cathetus2;
            hypotenuse = Math.sqrt(cathetus1 * cathetus1 + cathetus2 * cathetus2);
            perimeter = cathetus1 + cathetus2 + hypotenuse;
        } else if (codes.contains("c") && codes.contains("p")) {
            hypotenuse = features.get("c");
            area = features.get("p");
            double sin2alpha = 4 * area / (hypotenuse * hypotenuse);
            double alpha = findAlpha(sin2alpha) / 2;
            cathetus1 = Math.round(hypotenuse * (Math.sin(alpha)) * 100.0) / 100.0;
            cathetus2 = Math.round(hypotenuse * (Math.cos(alpha)) * 100.0) / 100.0;
            perimeter = cathetus1 + cathetus2 + hypotenuse;
        }
    }

    public double getHypotenuse() {
        return hypotenuse;
    }

    public double getCathetus1() {
        return cathetus1;
    }

    public double getCathetus2() {
        return cathetus2;
    }

    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public String toString(){
        return "Trójkąt prostokątny - Przyprostokątna 1: " + RoundClass.round(cathetus1, Constants.ROUND_PLACES) +
                " Przyprostokątna 2: " + RoundClass.round(cathetus2, Constants.ROUND_PLACES) +
                " Przeciwprostokątna: " + RoundClass.round(hypotenuse, Constants.ROUND_PLACES) + super.toString();
    }

    @Override
    public Circle getCircumcircle() throws NoCircumcircleException {
        return new Circle(hypotenuse/2);
    }

    @Override
    public Shape getDoubleShape() {
        return new RectangularTriangle(Map.of("a", cathetus1*Math.sqrt(2), "b", cathetus2*Math.sqrt(2)));
    }

}
