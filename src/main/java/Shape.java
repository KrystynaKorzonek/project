import java.util.Set;

public abstract class Shape implements Comparable<Shape> {
    protected double area;

    /*public Shape(double value) {
        if (value < 0)
            throw new IllegalArgumentException("Ujemna wartość!");
    }
*/
    // abstract static :(
    public int compareTo(Shape another){
        return Double.compare(this.area, another.area);
    }

    public static String getMessageChoiceFeature(){return "";}
    public static boolean isFeatureCode(String code){return false;}
    public static boolean isProperSetOfFeatures(Set<String> features){return false;}

}

