import java.util.Map;
import java.util.Set;

public class Square extends Shape{
    private double side;
    private double diagonal;

    public static String getMessageChoiceFeature(){
        return "b - bok, d - przekątna, p - pole";
    }
    public static boolean isFeatureCode(String code){
        return code.equals("b") || code.equals("d") || code.equals("p");
    }

    public static boolean isProperSetOfFeatures(Set<String> features){
        if (features.size()==1)
            if (features.contains("b") || features.contains("d") || features.contains("p"))
                return true;
        return false;
    }


    private void setFeatures(String feature, double value){
        switch (feature){
            case "p" -> {
                area = value;
                side = Math.sqrt(area);
                diagonal = Math.sqrt(2)*side;
            }
            case "b" -> {
                side = value;
                area = side*side;
                diagonal = Math.sqrt(2)*side;
            }
            case "d" -> {
                diagonal = value;
                side = diagonal/Math.sqrt(2);
                area = side*side;
            }
        }
        perimeter = 4*side;
    }
    public Square(Map<String, Double> features){
        if (features.size() != 1)
            throw new IllegalArgumentException("Too many features\n(that should never happen...)");
        for (String feature : features.keySet()) { //poprawić!
            double value = features.get(feature);
            setFeatures(feature, value);
        }
    }

    public String toString(){
        return "Kwadrat - bok: " + side + " przekątna: " + diagonal + super.toString();
    }

    @Override
    public Circle getCircumcircle() {
        return new Circle(diagonal/2);
    }
    @Override
    public Square getDoubleShape(){
        return new Square(Map.of("b", side*Math.sqrt(2)));
    }

}
