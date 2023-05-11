import java.util.Map;
import java.util.Set;

public class CircleFactory extends ShapeFactory{

    @Override
    public Shape create(Map<String, Double> features) {
        return(new Circle(features));
    }

    @Override
    public boolean isProperSetOfFeatures(Set<String> features) {
        if (features.size()==1)
            if (features.contains("r") || features.contains("d") || features.contains("p")  || features.contains("l"))
                return true;
        return false;
    }

    @Override
    public boolean isFeatureCode(String code) {
        return code.equals("r") || code.equals("d") || code.equals("p") || code.equals("l");
    }

    @Override
    public String getMessageChoiceFeature() {
        return "r - promień, d - średnica, p - pole, l - obwód";
    }
}
