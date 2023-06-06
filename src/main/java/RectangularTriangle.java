import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
public class RectangularTriangle extends Shape{
    private double hypotenuse;
    private double cathetus1; //catheus1 <= catheus2
    private double cathetus2;

    public boolean canBeIsosceles(){
        return cathetus1==cathetus2;
    }

    public static Shape toIsoscelesIfPossible(RectangularTriangle r){
        if (r.canBeIsosceles())
            return new IsoscelesTriangle(Map.of("b", r.getCathetus1(), "a", r.getHypotenuse()));
        return r;


    }


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

        if(hypotenuse < cathetus1 || hypotenuse < cathetus2)
            throw new IllegalArgumentException("Niespełniony warunek trójkąta prostokątnego");
        if (cathetus2 < cathetus1){
            double temp = cathetus1;
            cathetus1 = cathetus2;
            cathetus2 = temp;
        }
        if(hypotenuse< Constants.MIN_ATTR_VAL || cathetus1< Constants.MIN_ATTR_VAL || area < Constants.MIN_ATTR_VAL ||
                cathetus1> Constants.MAX_ATTR_VAL || cathetus2 < Constants.MIN_ATTR_VAL ||
                cathetus2> Constants.MAX_ATTR_VAL || hypotenuse> Constants.MAX_ATTR_VAL || area > Constants.MAX_ATTR_VAL){
            throw new IllegalArgumentException("Bad values of figure");
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


    public String toString(Language lang){
        switch(lang){
            case POLISH -> {
                return "Trójkąt prostokątny - Przyprostokątna 1: " + RoundClass.round(cathetus1) +
                        " Przyprostokątna 2: " + RoundClass.round(cathetus2) +
                        " Przeciwprostokątna: " + RoundClass.round(hypotenuse) + super.toString(lang);
            }
            case ENGLISH -> {
                return "Rectangular triangle - cathetuses: " + RoundClass.round(cathetus1) +
                        ", " + RoundClass.round(cathetus2) +
                        " hypotenuse: " + RoundClass.round(hypotenuse) + super.toString(lang);
            }
        }
        return "Wrong language";

    }

    @Override
    public Circle getCircumcircle() throws NoCircumcircleException {
        return new Circle(hypotenuse/2);
    }

    @Override
    public Shape getDoubleShape() {
        return new RectangularTriangle(Map.of("a", cathetus1*Math.sqrt(2), "b", cathetus2*Math.sqrt(2)));
    }

    public int compareTo(Shape another){
        if (another instanceof RectangularTriangle) {
            if (cathetus1 == ((RectangularTriangle)another).cathetus1 && cathetus2 == ((RectangularTriangle)another).cathetus2)
                return 0;
        }
        return Constants.NONZERO;
    }

}
