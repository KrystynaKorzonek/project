public class RoundClass {
    public static double round(double num, int places)
    {
        double scale = Math.pow(10, places);
        double roundedNum = Math.round(num * scale) / scale;
        return roundedNum;
    }
}
