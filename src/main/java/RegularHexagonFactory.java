import java.util.Map;
import java.util.Set;

public class RegularHexagonFactory extends ShapeFactory {
    private double side;
    private double area;
    private double perimeter;
    @Override
    public Shape create(Map<String, Double> features) throws NoCircumcircleException {
        this.side = features.getOrDefault("s", 0.0);
        this.area = features.getOrDefault("a", 0.0);
        this.perimeter = features.getOrDefault("p", 0.0);
        return new RegularHexagon(side, area, perimeter);
    }

    @Override
    public boolean isProperSetOfFeatures(Set<String> features) {
        if (features.size() == 1) {
            return features.contains("s") || features.contains("a") || features.contains("p");
        }
        return false;
    }

    @Override
    public boolean isFeatureCode(String code) {
        return code.equals("s") || code.equals("a") || code.equals("p");
    }

    @Override
    public String getMessageChoiceFeature(Language lang) {
        switch (lang) {
            case POLISH -> {
                return "s - bok, a - pole, p - obwód";
            }
            case ENGLISH -> {
                return "s - side, a - area, p - perimeter";
            }
        }
        return "Wrong language";
    }
}