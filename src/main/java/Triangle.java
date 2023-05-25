import java.time.LocalDateTime;

public class Triangle extends Shape{
    private double side1;
    private double side2;
    private double side3;

    public double getSide1(){return side1;}
    public double getSide2(){return side2;}
    public double getSide3(){return side3;}


    public Triangle(double side1, double side2, double side3){
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        this.verticesNumber = 3;
        this.dateTime = LocalDateTime.now();
        this.area = calculateArea();
        this.perimeter = calculatePerimeter();
    }

    private double calculateArea() {
        double p = (side1 + side2 + side3)/2;
        return Math.sqrt(p*(p-side1)*(p-side2)*(p-side3));
    }

    private double calculatePerimeter(){
        return side1 + side2 + side3;
    }

    @Override
    public Circle getCircumcircle() throws NoCircumcircleException {
        double r = side1*side2*side3/(4*area);
        if(r < 0){
            throw new NoCircumcircleException("Brak okregu opisanego na trojkacie");
        }
        return new Circle(r);
    }

    @Override
    public Shape getDoubleShape() {
        return new Triangle(side1*Math.sqrt(2), side2*Math.sqrt(2), side3*Math.sqrt(2));
    }

    public String toString(){
        return "Trojkat o bokach: " + RoundClass.round(side1) + ", " + RoundClass.round(side2) + ", " + RoundClass.round(side3) + super.toString();
    }
}
