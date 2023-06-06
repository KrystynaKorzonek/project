import java.util.Map;
import java.util.Set;

public class TriangleFactory extends ShapeFactory{
    private double side1, side2, side3; //side1 < side2 < side3



    @Override
    public Shape create(Map<String, Double> features) throws NoCircumcircleException, NoSuchTriangleException {
        side1 = features.get("a");
        side2 = features.get("b");
        side3 = features.get("c");

        if (side1 <= 0 || side2 <= 0 || side3 <= 0) {
            String m = "Boki muszą być dodatnie";
            if (StringManager.getLanguage() == Language.ENGLISH)
                m = "Sides must be positive";
            throw new NoSuchTriangleException(m);
        }
        if (side1 + side2 <= side3 || side1 + side3 <= side2 || side2 + side3 <= side1) {
            String m = "Niespełniony warunek trójkąta";
            if (StringManager.getLanguage() == Language.ENGLISH)
                m = "Triangle condition not satisfied";
            throw new NoSuchTriangleException(m);
        }
        sortSides();
        if (side1 == side2 && side2 == side3) {
            return new EquilateralTriangle(Map.of("a", side1));
        }
        // trójkąt prostokątny równoramienny -> utworzy się trójkąt równoramienny (tak jak ma być)
        if (side1 == side2) {
            return new IsoscelesTriangle(Map.of("a", side3, "b", side1));
        }
        if(side1 == side3){
            return new IsoscelesTriangle(Map.of("a", side2, "b", side1));
        }
        if(side2 == side3){
            return new IsoscelesTriangle(Map.of("a", side1, "b", side2));
        }
        if(side1*side1 + side2*side2 == side3*side3) {
            return new RectangularTriangle(Map.of("a", side1, "b", side2));
        }
        if(side1*side1 + side3*side3 == side2*side2) {
            return new RectangularTriangle(Map.of("a", side1, "b", side3));
        }
        if(side2*side2 + side3*side3 == side1*side1) {
            return new RectangularTriangle(Map.of("a", side2, "b", side3));
        }
        return new Triangle(side1, side2, side3);

    }

    private void sortSides(){
        double tmp;
        if (side1 > side2){
            tmp = side1;
            side1 = side2;
            side2 = tmp;
        }
        if (side2 > side3){
            tmp = side2;
            side2 = side3;
            side3 = tmp;
        }
        if (side1 > side2){
            tmp = side1;
            side1 = side2;
            side2 = tmp;
        }
    }

    @Override
    public boolean isProperSetOfFeatures(Set<String> features) {
        if (features.size()==3)
            if (features.contains("a") && features.contains("b") && features.contains("c"))
                return true;
        return false;
    }

    @Override
    public boolean isFeatureCode(String code) {
        return code.equals("a") || code.equals("b") || code.equals("c");
    }


    @Override
    public String getMessageChoiceFeature(Language lang){
        switch(lang){
            case POLISH -> {
                return "a - bok1, b - bok2, c - bok3";
            }
            case ENGLISH -> {
                return "a - side1, b - side2, c - side3";
            }
        }
        return "Wrong language";
    }
}
