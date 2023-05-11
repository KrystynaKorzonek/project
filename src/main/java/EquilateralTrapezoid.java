import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class EquilateralTrapezoid extends Shape{
    //a >= b!
    private double a, b;
    private double c;
    private double h;

    private void setFeaturesFromMap(Map<String, Double> features){
        a = features.get("a");
        //todo
    }


    public EquilateralTrapezoid(Map<String, Double> features) {
        this.verticesNumber = 4;
        this.dateTime = LocalDateTime.now();
        Set<String> codes = new TreeSet<>(features.keySet());
        if (codes.contains("a") && codes.contains("b") && codes.contains("h")) {
            //double p = ..., double c = ...
            //features.put("p", p);
            //features.put("c", c);
        }
        // TODO...
        setFeaturesFromMap(features);

    }
    @Override
    public Circle getCircumcircle() throws NoCircumcircleException {
        return null;
    }

    @Override
    public Shape getDoubleShape() {
        return null;
    }
}
