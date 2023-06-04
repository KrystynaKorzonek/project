import java.time.LocalDateTime;

public class RegularHexagon extends Shape{
    private double side;

    public double getSide(){return side;}

    public RegularHexagon(double side, double area, double perimeter){
        this.side = side;
        this.area = area;
        this.perimeter = perimeter;
        this.dateTime = LocalDateTime.now();

        if(side == 0.0){
            if(area == 0.0){
                this.side = perimeter/6;
                this.area = calculateArea();
            }
            else if(perimeter == 0.0){
                this.side = Math.sqrt(area*2/Math.sqrt(3));
                this.perimeter = calculatePerimeter();
            }
        }
        else{
            if(area == 0.0)
                this.area = calculateArea();
            else if(perimeter == 0.0)
                this.perimeter = calculatePerimeter();
        }


    }

    private double calculateArea(){
        return side*side*Math.sqrt(3)/2;
    }

    private double calculatePerimeter(){
        return 6*side;
    }

    @Override
    public Circle getCircumcircle() throws NoCircumcircleException {
        return new Circle(side);
    }

    @Override
    public Shape getDoubleShape() {
        return new RegularHexagon(side*Math.sqrt(2), area*2, perimeter*2);
    }

    public String toString(Language lang){
        switch(lang){
            case POLISH -> {
                return "Sześciokąt foremny o boku: " + RoundClass.round(side) + super.toString(lang);
            }
            case ENGLISH -> {
                return "Regular hexagon with side: " + RoundClass.round(side) + super.toString(lang);
            }
        }
        return "Wrong language";

    }

    public int compareTo(Shape shape){
        if(shape instanceof RegularHexagon){
            return Double.compare(this.side, ((RegularHexagon) shape).side);
        }
        else{
            return super.compareTo(shape);
        }
    }
}
