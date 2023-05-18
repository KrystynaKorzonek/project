import java.util.Map;
import java.util.Set;

public class RectangleFactory extends ShapeFactory{
    @Override
    public Shape create(Map<String, Double> features) {
        Rectangle created = new Rectangle(features);
        return Rectangle.toSquareIfPossible(created);
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
        return code.equals("a") || code.equals("b") || code.equals("e") || code.equals("p");
    }

    @Override
    public String getMessageChoiceFeature() {
        return "a - pierwszy bok, b - drugi bok, e - przekątna, p - pole";
    }
}
