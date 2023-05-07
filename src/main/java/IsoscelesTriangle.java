import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
public class IsoscelesTriangle extends Shape{
    private double side;
    private double height;
    private double area;
    private double base;
    private double perimeter;

    public static String getMessageChoiceFeature(){
        return "b - bok, h - wysokość, p - pole, a - podstawa";
    }
    public static boolean isFeatureCode(String code){
        return code.equals("b") || code.equals("h") || code.equals("p") || code.equals("a");
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

    public IsoscelesTriangle(Map<String, Double> features){
        this.verticesNumber = 3;
        this.dateTime = LocalDateTime.now();
        if (features.size() != 2)
            throw new IllegalArgumentException("Too many features\n(that should never happen...)");
        Set<String> codes = new TreeSet<>(features.keySet());
        if (!isProperSetOfFeatures(codes))
            throw new IllegalArgumentException("Wrong features\n(that should never happen...)");
        if (codes.contains("b") && codes.contains("h")){
            side = features.get("b");
            height = features.get("h");
            base = 2*Math.sqrt(side*side - height*height);
            area = base*height/2;
        }
        else if (codes.contains("b") && codes.contains("p")){
            side = features.get("b");
            area = features.get("p");
            base = Math.sqrt(2*side*side-2*side*side*Math.sqrt(1-(2*area/(side*side))*(2*area/(side*side))));
            height = 2*area/base;
        }
        else if (codes.contains("b") && codes.contains("a")){
            side = features.get("b");
            base = features.get("a");
            height = Math.sqrt(side*side - base*base/4);
            area = base*height/2;
        }
        else if (codes.contains("h") && codes.contains("p")){
            height = features.get("h");
            area = features.get("p");
            base = 2*area/height;
            side = Math.sqrt(base*base/4 + height*height);
        }
        else if (codes.contains("h") && codes.contains("a")){
            height = features.get("h");
            base = features.get("a");
            side = Math.sqrt(base*base/4 + height*height);
            area = base*height/2;
        }
        else if (codes.contains("p") && codes.contains("a")){
            area = features.get("p");
            base = features.get("a");
            height = 2*area/base;
            side = Math.sqrt(base*base/4 + height*height);
        }
        perimeter = 2*side + base;
    }

    public Double getSide(){
        return side;
    }
    public Double getHeight(){
        return height;
    }
    public Double getBase(){
        return base;
    }

    public String toString(){
        return "Trójkąt równoramienny - bok: " + RoundClass.round(side, Constants.ROUND_PLACES) +
                " wysokość: " + RoundClass.round(height, Constants.ROUND_PLACES) +
                " podstawa: " + RoundClass.round(base, Constants.ROUND_PLACES) +
                " pole: " + RoundClass.round(area, Constants.ROUND_PLACES) +
                " obwód: " + RoundClass.round(perimeter, Constants.ROUND_PLACES);
    }

    public IsoscelesTriangle getDoubleShape(){
        return new IsoscelesTriangle(Map.of("a", base*Math.sqrt(2), "h", height*Math.sqrt(2)));
    }
    @Override
    public Circle getCircumcircle() {
        return new Circle(base*side*side/(4*area));
    }




}
