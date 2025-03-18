package shapes;

import io.Language;

import java.util.Map;
import java.util.Set;

public class EquilateralTriangleFactory extends ShapeFactory {
    @Override
    public Shape create(Map<String, Double> features) {
        return new EquilateralTriangle(features);
    }

    @Override
    public boolean isProperSetOfFeatures(Set<String> features) {
        if (features.size() == 1)
            if (features.contains("a") || features.contains("h") || features.contains("p"))
                return true;
        return false;
    }

    @Override
    public boolean isFeatureCode(String code) {
        return code.equals("a") || code.equals("h") || code.equals("p");
    }

    public String getMessageChoiceFeature() {
        return "a - bok, h - wysokosc, p - pole";
    }

    @Override
    public String getMessageChoiceFeature(Language lang) {
        switch (lang) {
            case POLISH -> {
                return "a - bok, h - wysokosc, p - pole";
            }
            case ENGLISH -> {
                return "a - side, h - height, p - area";
            }
        }
        return "Wrong language";
    }
}
