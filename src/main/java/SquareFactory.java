import java.util.Map;
import java.util.Set;

public class SquareFactory extends ShapeFactory {

    @Override
    public Shape create(Map<String, Double> features) {
        Square square = new Square(features);
        return square;
    }

    @Override
    public boolean isProperSetOfFeatures(Set<String> features) {
        if (features.size()==1)
            if (features.contains("b") || features.contains("d") || features.contains("p"))
                return true;
        return false;
    }

    @Override
    public boolean isFeatureCode(String code) {
        return code.equals("b") || code.equals("d") || code.equals("p");
    }

    @Override
    public String getMessageChoiceFeature() {
        return "b - bok, d - przekątna, p - pole";
    }
}
