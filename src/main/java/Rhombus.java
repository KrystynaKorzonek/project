import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
        TreeSet keys = new TreeSet(features.keySet());
        System.out.println(keys);
        switch (keys.toString()) {
            case "[b, p]" -> {
                String arr[] = new String[2];
                arr = (String[]) keys.toArray(arr);
                side = features.get(arr[0]);
                area = features.get(arr[1]);
                double help = Math.sqrt(Math.pow(side, 4) - area*area);
                diag1 = Math.sqrt(2) * Math.sqrt(help+side*side);
                diag2 = 2*Math.sqrt(side*side+area) - diag1;

            }
            case "[b, e]" -> {
                String arr[] = new String[2];
                arr = (String[]) keys.toArray(arr);
                side = features.get(arr[0]);
                diag1 = features.get(arr[1]);
                diag2 = 2*Math.sqrt(side*side - diag1*diag1/4);
                area = (diag1 * diag2)/2;
            }
            case "[b, f]" -> {
                String arr[] = new String[2];
                arr = (String[]) keys.toArray(arr);
                side = features.get(arr[0]);
                diag2 = features.get(arr[1]);
                diag1 = 2*Math.sqrt(side*side - diag2*diag2/4);
                area = (diag1 * diag2)/2;
            }
            case "[e, f]" -> {
                String arr[] = new String[2];
                arr = (String[]) keys.toArray(arr);
                diag1 = features.get(arr[0]);
                diag2 = features.get(arr[1]);
                area = (diag1 * diag2)/2;
                side = Math.sqrt((diag1*diag1 + diag2*diag2)/4);
            }
            case "[e, p]" -> {
                String arr[] = new String[2];
                arr = (String[]) keys.toArray(arr);
                diag1 = features.get(arr[0]);
                area = features.get(arr[1]);
                diag2 = 2*area/diag1;
                side = Math.sqrt((diag1*diag1 + diag2*diag2)/4);
            }
            case "[f, p]" -> {
                String arr[] = new String[2];
                arr = (String[]) keys.toArray(arr);
                diag2 = features.get(arr[0]);
                area = features.get(arr[1]);
                diag1 = 2*area/diag2;
                side = Math.sqrt((diag1*diag1 + diag2*diag2)/4);
            }
        }

    }

    public String toString(){
        return "Romb - bok: " + side + " przekątne: " + diag1 + ", " + diag2 + " pole:" + area;
    }
}
