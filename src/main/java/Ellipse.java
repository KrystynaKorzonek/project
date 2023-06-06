import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

public class Ellipse extends Shape {
    private double semiMajorAxis;
    private double semiMinorAxis;

    public boolean canBeCircle() {
        return (semiMinorAxis == semiMajorAxis);
    }

    public static Shape toCircleIfPossible(Ellipse e) {
        if (e.canBeCircle())
            return new Circle(Map.of("r", e.getSemiMajorAxis()));
        return e;
    }

    public Ellipse(Map<String, Double> features) {
        this.verticesNumber = Integer.MAX_VALUE;
        this.dateTime = LocalDateTime.now();
        if (features.size() != 2)
            throw new IllegalArgumentException("Too many features\n(that should never happen...)");
        Set<String> codes = new TreeSet<>(features.keySet());
        if (codes.contains("a") && codes.contains("b")) {
            semiMinorAxis = features.get("a");
            semiMajorAxis = features.get("b");
            area = Math.PI * semiMinorAxis * semiMajorAxis;
        } else if (codes.contains("a") && codes.contains("p")) {
            semiMinorAxis = features.get("a");
            area = features.get("p");
            semiMajorAxis = area / (Math.PI * semiMinorAxis);
        } else if (codes.contains("b") && codes.contains("p")) {
            semiMajorAxis = features.get("b");
            area = features.get("p");
            semiMinorAxis = area / (Math.PI * semiMajorAxis);
        } else
            throw new IllegalArgumentException("Wrong features\n(that should never happen...)");

        if (semiMajorAxis < semiMinorAxis) {
            double temp = semiMajorAxis;
            semiMajorAxis = semiMinorAxis;
            semiMinorAxis = temp;
        }

        perimeter = Math.PI * (semiMajorAxis + semiMinorAxis);
        if (semiMajorAxis < Constants.MIN_ATTR_VAL || semiMinorAxis < Constants.MIN_ATTR_VAL ||
                area < Constants.MIN_ATTR_VAL || semiMajorAxis > Constants.MAX_ATTR_VAL ||
                semiMinorAxis > Constants.MAX_ATTR_VAL || area > Constants.MAX_ATTR_VAL) {
            throw new IllegalArgumentException("Bad values of figure");
        }
    }

    public int compareTo(Shape another) {
        if (another instanceof Ellipse) {
            if (Double.compare(semiMajorAxis, ((Ellipse) another).semiMajorAxis) == 0 &&
                    Double.compare(semiMinorAxis, ((Ellipse) another).semiMinorAxis) == 0)
                return 0;
        }
        return Constants.NONZERO;
    }

    public double getSemiMajorAxis() {
        return semiMajorAxis;
    }

    public double getSemiMinorAxis() {
        return semiMinorAxis;
    }

    public double getArea() {
        return area;
    }

    public String toString(Language lang) {
        switch (lang) {
            case POLISH -> {
                return "Elipsa: polos mala: " + RoundClass.round(semiMinorAxis) +
                        ", polos wielka: " + RoundClass.round(semiMajorAxis) + super.toString(lang);
            }
            case ENGLISH -> {
                return "Ellipse - semiMinorAxis: " + RoundClass.round(semiMinorAxis) + " semiMajorAxis: " +
                        RoundClass.round(semiMajorAxis) + super.toString(lang);
            }
        }
        return "Wrong language";

    }

    @Override
    public Circle getCircumcircle() throws NoCircumcircleException {
        String message = null;
        switch (StringManager.getLanguage()) {
            case POLISH -> {
                message = "Elipsa nie ma koła opisanego";
            }
            case ENGLISH -> {
                message = "Ellipse has no circumcircle";
            }
        }
        throw new NoCircumcircleException(message);
    }

    @Override
    public Shape getDoubleShape() {
        return new Ellipse(Map.of("a", semiMinorAxis, "b", semiMajorAxis));
    }
}
