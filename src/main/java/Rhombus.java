import java.util.Map;
import java.util.Set;

public class Rhombus extends Shape {
    private double side;
    private double diag1, diag2; //czy odróżniać krótszą i dłuższą??
    public static String getMessageChoiceFeature(){
        return "b - bok, e - przekątna, f - druga przekątna, p - pole"; //?
    }
    public static boolean isFeatureCode(String code){
        return code.equals("b") || code.equals("e") || code.equals("f") || code.equals("p");
    }

    public static boolean isProperSetOfFeatures(Set<String> features){
        if (features.size()==2) {
            for (String code : features)
                if (!isFeatureCode(code))
                    return false;
            return true;
        }
        return false;
    }

    public Rhombus(Map<String, Double> features){
        if (features.size() != 2)
            throw new IllegalArgumentException("Too many features\n(that should never happen...)");
        /* TODO!
        for (String feature : features.keySet()) { //:(
            switch (feature) {
                case "b" -> {

                }
                case "e" -> {

                }
                case "f" -> {

                }
                case "p" -> {

                }
            }
        }
         */

    }

    public String toString(){
        return "Romb - bok: " + side + " przekątne: " + diag1 + ", " + diag2 + " pole:" + area;
    }
}
