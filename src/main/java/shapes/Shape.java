package shapes;

import utilities.RoundClass;
import io.Language;

import java.time.LocalDateTime;
import java.util.Comparator;


public abstract class Shape implements Comparable<Shape> {
    protected double area;
    protected double perimeter;
    protected int verticesNumber;
    protected LocalDateTime dateTime;

    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getVerticesNumber() {
        return verticesNumber;
    }

    public String toString(Language lang) {
        switch (lang) {
            case POLISH -> {
                return " pole:" + RoundClass.round(area) + " obwód:" + RoundClass.round(perimeter) +
                        " data utworzenia:" + dateTime;
            }
            case ENGLISH -> {
                return " area:" + RoundClass.round(area) + " perimeter: " +
                        RoundClass.round(perimeter) + " creation date:" + dateTime;
            }
        }
        return "Wrong language";

    }

    public int compareTo(Shape another) {
        return Double.compare(this.area, another.area);
    }

    public abstract Circle getCircumcircle() throws NoCircumcircleException;

    public abstract Shape getDoubleShape();


}

class AreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape s1, Shape s2) {
        return Double.compare(s1.getArea(), s2.getArea());
    }
}

class PerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape s1, Shape s2) {
        return Double.compare(s1.getPerimeter(), s2.getPerimeter());
    }
}

class DateTimeComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape s1, Shape s2) {
        return s1.getDateTime().compareTo(s2.getDateTime());
    }
}