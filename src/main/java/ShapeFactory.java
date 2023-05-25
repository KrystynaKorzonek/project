import java.util.Map;
import java.util.Set;

//TODO:
/*
ShapeFactory lepiej współpracujące z DataTakerem
(te osobno stringi tak rażą... -> całe do DataTakera?)

 */


public abstract class ShapeFactory {
    public abstract Shape create(Map<String, Double> features) throws NoCircumcircleException;
    public abstract boolean isProperSetOfFeatures(Set<String> features);
    public abstract boolean isFeatureCode(String code);
    public abstract String getMessageChoiceFeature();
}
