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
        this.a = features.getOrDefault("a", 0.0);
        this.b = features.getOrDefault("b", 0.0);
        this.c = features.getOrDefault("c", 0.0);
        this.h = features.getOrDefault("h", 0.0);
        this.area = features.getOrDefault("p", 0.0);
    }


    public EquilateralTrapezoid(Map<String, Double> features) {
        this.verticesNumber = 4;
        this.dateTime = LocalDateTime.now();
        Set<String> codes = new TreeSet<>(features.keySet());
        setFeaturesFromMap(features);

        if(c == 0.0){
            if(a == 0.0){
                a = 2*area/h - b;
            }
            else if(b == 0.0){
                b = 2*area/h - a;
            }
            else if(h == 0.0){
                h = 2*area/(a+b);
            }
            else if(area == 0.0){
                area = (a+b)*h/2;
            }

            c = Math.sqrt(((a-b)*(a-b)/4) + h*h);
        }
        else{
            if(a != 0.0 && b != 0.0){
                h = Math.sqrt(c*c - ((a-b)*(a-b)/4));
                area = (a+b)*h/2;
            }
            else if(a != 0.0 && h != 0.0){
                b = a - Math.sqrt(4*(c*c - h*h));
                area = (a+b)*h/2;
            }
            else if (b != 0.0 && h != 0.0){
                a = Math.sqrt(4*(c*c - h*h)) + b;
                area = (a+b)*h/2;
            }
            else if(area != 0.0 && h != 0.0){
                b = area/h - Math.sqrt(c*c - h*h);
                a = 2*area/h - b;
            }
            else{
                //TODO: throw exception
                throw new IllegalArgumentException("Nie obsługiwany zestaw parametrów");
            }
        }
        perimeter = a + b + 2*c;
        if (a < b){
            double tmp = a;
            a = b;
            b = tmp;
        }


    }
    public String toString(){
        return "Trapez równoramienny - dłuższa podstawa: " + RoundClass.round(a) + " krótsza podstawa: " + RoundClass.round(b) + " ramie: " + RoundClass.round(c) + " wysokość: " + RoundClass.round(h) + super.toString();
    }
    @Override
    public Circle getCircumcircle() throws NoCircumcircleException {
        RectangularTriangle triangle = new RectangularTriangle(Map.of("a", (a+b)/2, "b", h));
        return new Circle(triangle.getCathetus1()*triangle.getCathetus2()*triangle.getHypotenuse()/triangle.getArea());
    }

    double getLagerBase(){
        return a;
    }
    double getShorterBase(){
        return b;
    }
    double getLeg(){
        return c;
    }
    double getHeight(){
        return h;
    }

    @Override //TODO!
    public Shape getDoubleShape() {
        return null;
    }
}
