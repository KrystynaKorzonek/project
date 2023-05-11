import java.util.Map;
import java.util.Set;

public class RhombusFactory extends ShapeFactory{

    @Override
    public Shape create(Map<String, Double> features) {
        return new Rhombus(features);
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
        return code.equals("b") || code.equals("e") || code.equals("f") || code.equals("p");
    }

    @Override
    public String getMessageChoiceFeature() {
        return "b - bok, e - przekątna, f - druga przekątna, p - pole";
    }
}
