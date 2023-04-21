import java.util.Comparator;
import java.util.Map;
import java.util.Set;

public abstract class Shape implements Comparable<Shape> {
    protected double area;
    protected double perimeter;
    public double getArea(){
        return area;
    }
    public double getPerimeter(){
        return perimeter;
    }
    public String toString(){
        return " pole:" + area + " obwód:" + perimeter;
    }


    /*public Shape(double value) { //TODO: sprawdzanie wyjątków dla całego shape'a z nową sygnaturą konstruktora?
        if (value < 0)
            throw new IllegalArgumentException("Ujemna wartość!");
    }
*/
    // abstract static :(
    public int compareTo(Shape another){
        return Double.compare(this.area, another.area);
    }
    public abstract Circle getCircumcircle() throws NoCircumcircleException;
    public abstract Shape getDoubleShape();
    public static String getMessageChoiceFeature(){return "";}
    public static boolean isFeatureCode(String code){return false;}
    public static boolean isProperSetOfFeatures(Set<String> features){return false;}


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