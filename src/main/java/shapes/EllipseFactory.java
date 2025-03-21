package shapes;

import io.Language;

import java.util.Map;
import java.util.Set;

public class EllipseFactory extends ShapeFactory {
    @Override
    public Shape create(Map<String, Double> features) {
        Ellipse created = new Ellipse(features);
        return Ellipse.toCircleIfPossible(created);


    }

    @Override
    public boolean isProperSetOfFeatures(Set<String> features) {
        if (features.size() == 2) {
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

    public String getMessageChoiceFeature() {
        return "a - półoś mała, b - półoś wielka, p - pole";
    }

    @Override
    public String getMessageChoiceFeature(Language lang) {
        switch (lang) {
            case POLISH -> {
                return "a - półoś mała, b - półoś wielka, p - pole";
            }
            case ENGLISH -> {
                return "a - semiMinorAxis, b - semiMajorAxis, p - area";
            }
        }
        return "Wrong language";
    }
}
