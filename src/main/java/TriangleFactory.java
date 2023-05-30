import java.util.Map;
import java.util.Set;

public class TriangleFactory extends ShapeFactory{
    private double side1, side2, side3;



    @Override
    public Shape create(Map<String, Double> features) throws NoCircumcircleException {
        side1 = features.get("a");
        side2 = features.get("b");
        side3 = features.get("c");

        if (side1 <= 0 || side2 <= 0 || side3 <= 0) {
            throw new NoCircumcircleException("Boki muszą być dodatnie");
        }
        if (side1 + side2 <= side3 || side1 + side3 <= side2 || side2 + side3 <= side1) {
            throw new NoCircumcircleException("Niespełniony warunek trójkąta");
        }
        if (side1 == side2 && side2 == side3) {
            return new Equilateral_Triangle(Map.of("a", side1));
        }
        if (side1 == side2) {
            return new IsoscelesTriangle(Map.of("a", side3, "b", side1));
        }
        if(side1 == side3){
            return new IsoscelesTriangle(Map.of("a", side2, "b", side1));
        }
        if(side2 == side3){
            return new IsoscelesTriangle(Map.of("a", side1, "b", side2));
        }
        if(side1*side1 + side2*side2 == side3*side3) {
            return new RectangularTriangle(Map.of("a", side1, "b", side2));
        }
        if(side1*side1 + side3*side3 == side2*side2) {
            return new RectangularTriangle(Map.of("a", side1, "b", side3));
        }
        if(side2*side2 + side3*side3 == side1*side1) {
            return new RectangularTriangle(Map.of("a", side2, "b", side3));
        }
        return new Triangle(side1, side2, side3);

    }

    @Override
    public boolean isProperSetOfFeatures(Set<String> features) {
        if (features.size()==3)
            if (features.contains("a") && features.contains("b") && features.contains("c"))
                return true;
        return false;
    }

    @Override
    public boolean isFeatureCode(String code) {
        return code.equals("a") || code.equals("b") || code.equals("c");
    }

    public String getMessageChoiceFeature() {
        return "a - bok1, b - bok2, c - bok3";
    }
    @Override
    public String getMessageChoiceFeature(Language lang){
        switch(lang){
            case POLISH -> {
                return "a - bok1, b - bok2, c - bok3";
            }
            case ENGLISH -> {
                return "a - side1, b - side2, c - side3";
            }
        }
        return "Wrong language";
    }
}
