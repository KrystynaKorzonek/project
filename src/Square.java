public class Square extends Shape{
    private double side;
    private double diagonal;

    public static String getMessageChoiceFeature(){
        return "b - bok, d - przekątna, p - pole";
    }
    public static boolean isFeatureCode(String code){
        if (code.equals("b") || code.equals("d") || code.equals("p"))
            return true;
        return false;
    }

    public Square(String feature, double value){
        super(value);
        switch (feature){
            case "p" -> {
                area = value;
                side = Math.sqrt(area);
                diagonal = Math.sqrt(2)*side;
            }
            case "b" -> {
                side = value;
                area = side*side;
                diagonal = Math.sqrt(2)*side;
            }
            case "d" -> {
                diagonal = value;
                side = diagonal/Math.sqrt(2);
                area = side*side;
            }
        }
    }

    public String toString(){
        return "Kwadrat - bok: " + side + " przekątna: " + diagonal + " pole:" + area;
    }
}
