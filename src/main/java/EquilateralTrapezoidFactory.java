import java.util.Map;
import java.util.Set;
public class EquilateralTrapezoidFactory extends ShapeFactory{
    @Override
    public Shape create(Map<String, Double> features) {
        return new EquilateralTrapezoid(features);
    }

    @Override
    public boolean isProperSetOfFeatures(Set<String> features) {
        if (features.size()==3) {
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

    @Override
    public String getMessageChoiceFeature() {
        return "a - dłuższa podstawa, b - krótsza podstawa, c - ramie, h - wysokość, p - pole";
    }
}
