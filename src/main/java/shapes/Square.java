package shapes;

import utilities.Constants;
import utilities.RoundClass;
import io.*;

import java.time.LocalDateTime;
import java.util.Map;

public class Square extends Shape {
    private double side;
    private double diagonal;

    public double getSide() {
        return side;
    }

    public double getDiagonal() {
        return diagonal;
    }

    private void setFeatures(String feature, double value) {
        switch (feature) {
            case "p" -> {
                area = value;
                side = Math.sqrt(area);
                diagonal = Math.sqrt(2) * side;
            }
            case "b" -> {
                side = value;
                area = side * side;
                diagonal = Math.sqrt(2) * side;
            }
            case "d" -> {
                diagonal = value;
                side = diagonal / Math.sqrt(2);
                area = side * side;
            }
        }
        perimeter = 4 * side;
    }

    public Square(Map<String, Double> features) {
        if (features.size() != 1)
            throw new IllegalArgumentException("Too many features\n(that should never happen...)");
        for (String feature : features.keySet()) { //poprawić!
            double value = features.get(feature);
            setFeatures(feature, value);
        }
        this.dateTime = LocalDateTime.now();
        this.verticesNumber = 4;
        if (side < Constants.MIN_ATTR_VAL || area < Constants.MIN_ATTR_VAL || side > Constants.MAX_ATTR_VAL ||
                area > Constants.MAX_ATTR_VAL || diagonal < Constants.MIN_ATTR_VAL || diagonal > Constants.MAX_ATTR_VAL) {
            throw new IllegalArgumentException("Bad values of figure");
        }
    }

    public String toString(Language lang) {
        switch (lang) {
            case POLISH -> {
                return "Kwadrat - bok: " + RoundClass.round(side) +
                        " przekątna: " + RoundClass.round(diagonal) + super.toString(lang);
            }
            case ENGLISH -> {
                return "shapes.Square - sode: " + RoundClass.round(side) + " diagonal: " +
                        RoundClass.round(diagonal) + super.toString(lang);
            }
        }
        return "Wrong language";

    }

    @Override
    public Circle getCircumcircle() {
        return new Circle(diagonal / 2);
    }

    @Override
    public Square getDoubleShape() {
        return new Square(Map.of("b", side * Math.sqrt(2)));
    }

    public int compareTo(Shape another) {
        if (another instanceof Square) {
            return Double.compare(this.side, ((Square) another).side);
        }
        return Constants.NONZERO;
    }


}
