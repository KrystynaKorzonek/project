public class Circle extends Shape{
    private double radius;
    private double diameter;
    private double circumference;


    public static String getMessageChoiceFeature(){
        return "r - promień, d - średnica, p - pole, l - obwód";
    }
    public static boolean isFeatureCode(String code){
        if (code.equals("r") || code.equals("d") || code.equals("p") || code.equals("l"))
            return true;
        return false;
    }
    private void setFeaturesFromRadius(double radius){
        this.radius = radius;
        this.diameter = 2*radius;
        this.circumference = 2 * Math.PI * radius;
        this.area = Math.PI * radius * radius;
    }

    public Circle(String feature, double value){
        super(value);
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

    public String toString(){
        return "Koło - promień: " + radius + " średnica: " + diameter + " obwód:" + circumference + " pole:" + area;
    }
}

