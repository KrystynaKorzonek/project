import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Equilateral_Triangle extends Shape {
    private double side;
    private double height;

    public Equilateral_Triangle(Map<String, Double> features){
        this.verticesNumber = 3;
        this.dateTime = LocalDateTime.now();
        if (features.size() != 1)
            throw new IllegalArgumentException("Too many features\n(that should never happen...)");
        for (String feature : features.keySet()) { //poprawić!
            double value = features.get(feature);
            setFeatures(feature, value);
        }
    }

    private void setFeatures(String feature, double value) {
        switch (feature) {
            case "p" -> {
                area = value;
                side = Math.sqrt(4 * area / Math.sqrt(3));
                height = Math.sqrt(3) * side/2;
            }
            case "a" -> {
                side = value;
                height = Math.sqrt(3) * side/2;
                area = side * height/2;
            }
            case "h" -> {
                height = value;
                side = height*2 / Math.sqrt(3);
                area = side * height/2;
            }
        }
        perimeter = 3 * side;
    }

    public String toString(){
        return "Trojkat rownoboczny - bok: " + RoundClass.round(side, Constants.ROUND_PLACES) +
                " wysokosc: " + RoundClass.round(height, Constants.ROUND_PLACES) + super.toString();
    }

    @Override
    public Circle getCircumcircle() {
        return new Circle(2*height/3);
    }

    @Override
    public Equilateral_Triangle getDoubleShape(){
        return new Equilateral_Triangle(Map.of("a", side*Math.sqrt(2)));
    }
}
