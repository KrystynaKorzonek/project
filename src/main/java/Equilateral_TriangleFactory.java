import java.util.Map;
import java.util.Set;

public class Equilateral_TriangleFactory extends ShapeFactory{
    @Override
    public Shape create(Map<String, Double> features) {
        return new Equilateral_Triangle(features);
    }

    @Override
    public boolean isProperSetOfFeatures(Set<String> features) {
        if (features.size()==1)
            if (features.contains("a") || features.contains("h") || features.contains("p"))
                return true;
        return false;
    }

    @Override
    public boolean isFeatureCode(String code) {
        return code.equals("a") || code.equals("h") || code.equals("p");
    }

    @Override
    public String getMessageChoiceFeature() {
        return "a - bok, h - wysokosc, p - pole";
    }
}
