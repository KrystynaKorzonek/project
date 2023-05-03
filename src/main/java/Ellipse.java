import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
public class Ellipse extends Shape{
    private double semiMajorAxis;
    private double semiMinorAxis;
    private double area;

    public static String getMessageChoiceFeature(){
        return "a - półoś mała, b - półoś wielka, p - pole";
    }
    public static boolean isFeatureCode(String code){
        return code.equals("a") || code.equals("b") || code.equals("p");
    }
    public static boolean isProperSetOfFeatures(Set<String> features){
        if (features.size()==2) {
            for (String code : features)
                if (!isFeatureCode(code))
                    return false;
            return true;
        }
        return false;
    }

    public Ellipse(Map<String, Double> features) {
        this.verticesNumber = Integer.MAX_VALUE;
        this.dateTime = LocalDateTime.now();
        if (features.size() != 2)
            throw new IllegalArgumentException("Too many features\n(that should never happen...)");
        Set<String> codes = new TreeSet<>(features.keySet());
        if (!isProperSetOfFeatures(codes))
            throw new IllegalArgumentException("Wrong features\n(that should never happen...)");
        if (codes.contains("a") && codes.contains("b")) {
            semiMinorAxis = features.get("a");
            semiMajorAxis = features.get("b");
            area = Math.PI * semiMinorAxis * semiMajorAxis;
        }
        else if(codes.contains("a") && codes.contains("p")){
            semiMinorAxis = features.get("a");
            area = features.get("p");
            semiMajorAxis = area/(Math.PI*semiMinorAxis);
        }
        else if(codes.contains("b") && codes.contains("p")){
            semiMajorAxis = features.get("b");
            area = features.get("p");
            semiMinorAxis = area/(Math.PI*semiMajorAxis);
        }
        else
            throw new IllegalArgumentException("Wrong features\n(that should never happen...)");

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

    public String toString(){
        return "Elipsa: polos mala: " + semiMinorAxis + ", polos wielka: " + semiMajorAxis + super.toString();
    }

    @Override
    public Circle getCircumcircle() throws NoCircumcircleException {
        throw new NoCircumcircleException("Ellipse has no circumcircle");
    }

    @Override
    public Shape getDoubleShape() {
        return new Ellipse(Map.of("a", semiMinorAxis, "b", semiMajorAxis));
    }
}
