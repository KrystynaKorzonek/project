import java.util.Map;
import java.util.Set;

public class EllipseFactory extends ShapeFactory{
    @Override
    public Shape create(Map<String, Double> features) {
         Ellipse created = new Ellipse(features);
         return Ellipse.toCircleIfPossible(created);


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
        return code.equals("a") || code.equals("b") || code.equals("p");
    }

    @Override
    public String getMessageChoiceFeature() {
        return "a - półoś mała, b - półoś wielka, p - pole";
    }
}
