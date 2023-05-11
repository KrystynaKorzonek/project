import java.util.Map;
import java.util.Set;

public class RectangularTriangleFactory extends ShapeFactory{
    @Override
    public Shape create(Map<String, Double> features) {
        return new RectangularTriangle(features);
    }

    @Override
    public boolean isProperSetOfFeatures(Set<String> features) {
        if (features.size()==2) {
            for (String code : features)
                if (!isFeatureCode(code))
                    return false;
            return true;
        }
        return false;
    }

    @Override
    public boolean isFeatureCode(String code) {
        return code.equals("a") || code.equals("b") || code.equals("c") || code.equals("p");
    }

    @Override
    public String getMessageChoiceFeature() {
        return "a - przyprostokątna 1, b - przyprostokątna 2, c - przeciwprostokątna, p - pole";
    }
}
