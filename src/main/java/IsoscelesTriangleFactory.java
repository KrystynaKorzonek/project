import java.util.Map;
import java.util.Set;

public class IsoscelesTriangleFactory extends ShapeFactory {
    // UWAGA! trójkąt prostokątny równoramienny będzie równoramienny, a nie prostokątny
    @Override
    public Shape create(Map<String, Double> features) {
        IsoscelesTriangle created = new IsoscelesTriangle(features);
        return IsoscelesTriangle.toEquilateralIfPossible(created);
    }

    @Override
    public boolean isProperSetOfFeatures(Set<String> features) {
        if (features.size() == 2) {
            for (String code : features)
                if (!isFeatureCode(code))
                    return false;
            return true;
        }
        return false;
    }

    @Override
    public boolean isFeatureCode(String code) {
        return code.equals("b") || code.equals("h") || code.equals("p") || code.equals("a");
    }

    public String getMessageChoiceFeature() {
        return "b - bok, h - wysokość, p - pole, a - podstawa";
    }

    @Override
    public String getMessageChoiceFeature(Language lang) {
        switch (lang) {
            case POLISH -> {
                return "b - bok, h - wysokość, p - pole, a - podstawa";
            }
            case ENGLISH -> {
                return "b - side, h - height, p - area, a - base";
            }
        }
        return "Wrong language";
    }
}
