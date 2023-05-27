import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Rhombus extends Shape {
    private double side;
    private double diag1, diag2; //czy odróżniać krótszą i dłuższą??
    public double getSide(){return side;}
    public boolean canBeSquare(){
        return diag1==diag2;
    }
    public static Shape toSquareIfPossible(Rhombus r){
        if (r.canBeSquare())
            return new Square(Map.of("b", r.getSide()));
        return r;
    }

    public Rhombus(Map<String, Double> features){
        this.dateTime = LocalDateTime.now();
        this.verticesNumber = 4;
        if (features.size() != 2)
            throw new IllegalArgumentException("Too many features\n(that should never happen...)");
        TreeSet keys = new TreeSet(features.keySet());
        switch (keys.toString()) {
            case "[b, p]" -> {
                String arr[] = new String[2];
                arr = (String[]) keys.toArray(arr);
                side = features.get(arr[0]);
                area = features.get(arr[1]);
                double help = Math.sqrt(Math.pow(side, 4) - area*area);
                diag1 = Math.sqrt(2) * Math.sqrt(help+side*side);
                diag2 = 2*Math.sqrt(side*side+area) - diag1;

            }
            case "[b, e]" -> {
                String arr[] = new String[2];
                arr = (String[]) keys.toArray(arr);
                side = features.get(arr[0]);
                diag1 = features.get(arr[1]);
                diag2 = 2*Math.sqrt(side*side - diag1*diag1/4);
                area = (diag1 * diag2)/2;
            }
            case "[b, f]" -> {
                String arr[] = new String[2];
                arr = (String[]) keys.toArray(arr);
                side = features.get(arr[0]);
                diag2 = features.get(arr[1]);
                diag1 = 2*Math.sqrt(side*side - diag2*diag2/4);
                area = (diag1 * diag2)/2;
            }
            case "[e, f]" -> {
                String arr[] = new String[2];
                arr = (String[]) keys.toArray(arr);
                diag1 = features.get(arr[0]);
                diag2 = features.get(arr[1]);
                area = (diag1 * diag2)/2;
                side = Math.sqrt((diag1*diag1 + diag2*diag2)/4);
            }
            case "[e, p]" -> {
                String arr[] = new String[2];
                arr = (String[]) keys.toArray(arr);
                diag1 = features.get(arr[0]);
                area = features.get(arr[1]);
                diag2 = 2*area/diag1;
                side = Math.sqrt((diag1*diag1 + diag2*diag2)/4);
            }
            case "[f, p]" -> {
                String arr[] = new String[2];
                arr = (String[]) keys.toArray(arr);
                diag2 = features.get(arr[0]);
                area = features.get(arr[1]);
                diag1 = 2*area/diag2;
                side = Math.sqrt((diag1*diag1 + diag2*diag2)/4);
            }
        }
        perimeter = 4*side;

    }

    public String toString(){
        return "Romb - bok: " + RoundClass.round(side) +
                " przekątne: " + RoundClass.round(diag1) +
                ", " + RoundClass.round(diag2) + super.toString();
    }

    @Override
    public Circle getCircumcircle() throws NoCircumcircleException {
        String message = null;
        switch (StringManager.getLanguage()){
            case POLISH -> {
                message = "Romb (nie będący kwadratem) nie ma koła opisanego";
            }
            case ENGLISH -> {
                message = "Rhombus (not square) has no circumcircle";
            }
        }
        throw new NoCircumcircleException(message);
    }
    @Override
    public Rhombus getDoubleShape(){
        return new Rhombus(Map.of("e", diag1*Math.sqrt(2), "f", diag2*Math.sqrt(2)));
    }

    public int compareTo(Shape another){
        if (another instanceof Rhombus) {
            if (diag1 == ((Rhombus)another).diag1 && diag2 == ((Rhombus)another).diag2)
                return 0;
        }
        return Constants.NONZERO;
    }
}
