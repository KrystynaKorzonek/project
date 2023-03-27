public abstract class Shape {
    protected double area;

    public Shape(double value) {
        if (value < 0)
            throw new IllegalArgumentException("Ujemna wartość!");
    }

    // abstract static :(
    public static String getMessageChoiceFeature(){return "";}
    public static boolean isFeatureCode(String code){return false;}


}

