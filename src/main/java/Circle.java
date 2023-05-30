import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

public class Circle extends Shape{
    private double radius;
    private double diameter;


    private void setFeaturesFromRadius(double radius){
        this.verticesNumber = Integer.MAX_VALUE;
        this.radius = radius;
        this.diameter = 2*radius;
        this.perimeter = 2 * Math.PI * radius;
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
        this.dateTime = LocalDateTime.now();
        if (features.size() != 1)
            throw new IllegalArgumentException("Too many features\n(that should never happen...)");
        for (String feature : features.keySet()) {
            double value = features.get(feature);
            setFeatures(feature, value);
        }
    }

    public Circle(double radius){
        this.dateTime = LocalDateTime.now();
        setFeaturesFromRadius(radius);
        if(radius< Constants.MIN_ATTR_VAL || area < Constants.MIN_ATTR_VAL || radius> Constants.MAX_ATTR_VAL ||
                area > Constants.MAX_ATTR_VAL){
            throw new IllegalArgumentException("Bad values of figure");
        }
    }

    public String toString(Language lang){
        switch(lang){
            case POLISH -> {
                return "Koło - promień: " + RoundClass.round(radius) + " średnica: " +
                        RoundClass.round(diameter) + super.toString(lang);
            }
            case ENGLISH -> {
                return "Circle - radius: " + RoundClass.round(radius) + " diameter: " +
                        RoundClass.round(diameter) + super.toString(lang);
            }
        }
        return "Wrong language";

    }

    public int compareTo(Shape another){
        if (another instanceof Circle) {
            if (radius == ((Circle)another).radius)
                return 0;
        }
        return Constants.NONZERO;
    }
    @Override
    public Circle getCircumcircle() {
        return new Circle(radius);
    }

    @Override
    public Circle getDoubleShape() {
        return new Circle(radius*Math.sqrt(2));
    }
}

