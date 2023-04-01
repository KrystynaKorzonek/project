import java.util.Map;
import java.util.Set;

public class Circle extends Shape{
    private double radius;
    private double diameter;
    private double circumference;


    public static String getMessageChoiceFeature(){
        return "r - promień, d - średnica, p - pole, l - obwód";
    }
    public static boolean isFeatureCode(String code){
        return code.equals("r") || code.equals("d") || code.equals("p") || code.equals("l");
    }
    public static boolean isProperSetOfFeatures(Set<String> features){
        if (features.size()==1)
            if (features.contains("r") || features.contains("d") || features.contains("p")  || features.contains("l"))
                return true;
        return false;
    }
    private void setFeaturesFromRadius(double radius){
        this.radius = radius;
        this.diameter = 2*radius;
        this.circumference = 2 * Math.PI * radius;
        this.area = Math.PI * radius * radius;
    }

    private void setFeatures(String feature, double value){
        double radius = 0;
        switch (feature){
            case "r" -> {
                radius = value;
            }
            case "d" -> {
                radius = value/2;
            }
            case "p" -> {
                radius = Math.sqrt(value/Math.PI);
            }
            case "l" -> {
                radius  = value/(2*Math.PI);
            }
        }
        setFeaturesFromRadius(radius);

    }

    public Circle(Map<String, Double> features){
        if (features.size() != 1)
            throw new IllegalArgumentException("Too many features\n(that should never happen...)");
        for (String feature : features.keySet()) {
            double value = features.get(feature);
            setFeatures(feature, value);
        }

    }

    public String toString(){
        return "Koło - promień: " + radius + " średnica: " + diameter + " obwód:" + circumference + " pole:" + area;
    }
}

