import java.util.Map;
import java.util.Set;

public class EquilateralTrapezoidFactory extends ShapeFactory {
    @Override
    public Shape create(Map<String, Double> features) {
        EquilateralTrapezoid created = new EquilateralTrapezoid(features);
        return EquilateralTrapezoid.toRectangleOrSquareIfPossible(created);
    }


    @Override
    public boolean isProperSetOfFeatures(Set<String> features) {
        if (features.size() == 3) {
            for (String code : features)
                if (!isFeatureCode(code))
                    return false;
            return true;
        }
        return false;
    }

    @Override
    public boolean isFeatureCode(String code) {
        return code.equals("a") || code.equals("b") || code.equals("c") || code.equals("h") || code.equals("p");
    }

    public String getMessageChoiceFeature() {
        return "a - dłuższa podstawa, b - krótsza podstawa, c - ramie, h - wysokość, p - pole";
    }

    @Override
    public String getMessageChoiceFeature(Language lang) {
        switch (lang) {
            case POLISH -> {
                return "a - dłuższa podstawa, b - krótsza podstawa, c - ramie, h - wysokość, p - pole";
            }
            case ENGLISH -> {
                return "a - longer base, b - shorter base, c - side, h - height, p - area";
            }
        }
        return "Wrong language";
    }
}
