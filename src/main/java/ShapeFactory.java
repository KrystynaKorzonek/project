import java.util.Map;
import java.util.Set;



public abstract class ShapeFactory {
    public abstract Shape create(Map<String, Double> features) throws NoCircumcircleException, NoSuchTriangleException;
    public abstract boolean isProperSetOfFeatures(Set<String> features);
    public abstract boolean isFeatureCode(String code);
    public abstract String getMessageChoiceFeature(Language lang);
}
