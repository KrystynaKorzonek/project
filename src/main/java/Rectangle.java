import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Rectangle extends Shape {
    private double side1;
    private double side2;
    private double diag;
    public static String getMessageChoiceFeature(){
        return "a - pierwszy bok, b - drugi bok, e - przekątna, p - pole"; //?
    }
    public static boolean isFeatureCode(String code){
        return code.equals("a") || code.equals("b") || code.equals("e") || code.equals("p");
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

    public Rectangle(Map<String, Double> features){
        this.verticesNumber = 4;
        this.dateTime = LocalDateTime.now();
        if (features.size() != 2)
            throw new IllegalArgumentException("Too many features\n(that should never happen...)");
        TreeSet keys = new TreeSet(features.keySet());
        System.out.println(keys);
        switch (keys.toString()) {
            case "[a, b]" -> {
                String arr[] = new String[2];
                arr = (String[]) keys.toArray(arr);
                side1 = features.get(arr[0]);
                side2 = features.get(arr[1]);
                diag = Math.sqrt(side1*side1 + side2*side2);
                area = side1 * side2;

            }
            case "[a, e]" -> {
                String arr[] = new String[2];
                arr = (String[]) keys.toArray(arr);
                side1 = features.get(arr[0]);
                diag = features.get(arr[1]);
                side2 = Math.sqrt(diag*diag - side1*side1);
                area = side1 * side2;
            }
            case "[a, p]" -> {
                String arr[] = new String[2];
                arr = (String[]) keys.toArray(arr);
                side1 = features.get(arr[0]);
                area = features.get(arr[1]);
                side2 = area/side1;
                diag = Math.sqrt(side1*side1 + side2*side2);
            }
            case "[b, e]" -> {
                String arr[] = new String[2];
                arr = (String[]) keys.toArray(arr);
                side2 = features.get(arr[0]);
                diag = features.get(arr[1]);
                side1 = Math.sqrt(diag*diag - side2*side2);
                area = side1 * side2;
            }
            case "[b, p]" -> {
                String arr[] = new String[2];
                arr = (String[]) keys.toArray(arr);
                side2 = features.get(arr[0]);
                area = features.get(arr[1]);
                side1 = area/side2;
                diag = Math.sqrt(side1*side1 + side2*side2);
            }
            case "[e, p]" -> {
                String arr[] = new String[2];
                arr = (String[]) keys.toArray(arr);
                diag = features.get(arr[0]);
                area = features.get(arr[1]);
                side1 = (Math.sqrt(diag * diag + 2*area) + Math.sqrt(diag * diag - 2*area))/2;
                side2 = (Math.sqrt(diag * diag + 2*area) - Math.sqrt(diag * diag - 2*area))/2;;
            }
        }
        perimeter = 2*(side1 + side2);

    }

    public String toString(){
        return "Prostokat - boki: " + side1 + ", " + side2 + " przekątna: " + diag + super.toString();
    }

    @Override
    public Circle getCircumcircle() {
        return new Circle(diag/2);
    }
    @Override
    public Rectangle getDoubleShape(){
        return new Rectangle(Map.of("a", side1*Math.sqrt(2), "b", side2*Math.sqrt(2)));
    }
}
