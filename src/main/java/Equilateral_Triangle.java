import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Equilateral_Triangle extends Shape {
    private double side;
    private double height;
    public double getSide() { return side;}
    public double getHeight() { return height;}

    public Equilateral_Triangle(Map<String, Double> features){
        this.verticesNumber = 3;
        this.dateTime = LocalDateTime.now();
        if (features.size() != 1)
            throw new IllegalArgumentException("Too many features\n(that should never happen...)");
        for (String feature : features.keySet()) { //poprawić!
            double value = features.get(feature);
            setFeatures(feature, value);
        }
        if(side< Constants.MIN_ATTR_VAL || height< Constants.MIN_ATTR_VAL || area < Constants.MIN_ATTR_VAL ||
                side> Constants.MAX_ATTR_VAL || height> Constants.MAX_ATTR_VAL || area > Constants.MAX_ATTR_VAL){
            throw new IllegalArgumentException("Bad values of figure");
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

    public int compareTo(Shape another){
        if (another instanceof Equilateral_Triangle) {
            if (side == ((Equilateral_Triangle)another).side && height == ((Equilateral_Triangle)another).height)
                return 0;
        }
        return Constants.NONZERO;
    }

    public String toString(Language lang){
        switch(lang){
            case POLISH -> {
                return "Trojkat rownoboczny - bok: " + RoundClass.round(side) +
                        " wysokosc: " + RoundClass.round(height) + super.toString(lang);
            }
            case ENGLISH -> {
                return "Equilateral triangle - side: " + RoundClass.round(side) +
                        " height: " + RoundClass.round(height) + super.toString(lang);
            }
        }
        return "Wrong language";

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
