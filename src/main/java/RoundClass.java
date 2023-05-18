public class RoundClass {
    private static int ROUND_PLACES = 2;
    public static double round(double num, int places)
    {
        double scale = Math.pow(10, places);
        double roundedNum = Math.round(num * scale) / scale;
        return roundedNum;
    }
    public static void setRoundPlaces(int places){
        ROUND_PLACES = places;
    }
    public static double round(double num){
        return round(num, ROUND_PLACES);
    }
}
