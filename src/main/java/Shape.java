import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;


public abstract class Shape implements Comparable<Shape> {
    protected double area;
    protected double perimeter;
    protected int verticesNumber;
    protected LocalDateTime dateTime;
    public double getArea(){
        return area;
    }
    public double getPerimeter(){
        return perimeter;
    }
    public LocalDateTime getDateTime(){
        return dateTime;
    }
    public int getVerticesNumber(){return verticesNumber;}
    public String toString(){
        return " pole:" + RoundClass.round(area) + " obwód:" + RoundClass.round(perimeter) +
                " data utworzenia:" + dateTime;
    }


    /*public Shape(double value) { //TODO: sprawdzanie wyjątków dla całego shape'a z nową sygnaturą konstruktora?
        if (value < 0)
            throw new IllegalArgumentException("Ujemna wartość!");
    }
*/
    // abstract static :(
    public int compareTo(Shape another){
        return Double.compare(this.area, another.area);
    }
    public abstract Circle getCircumcircle() throws NoCircumcircleException;
    public abstract Shape getDoubleShape();


}

class OneStageComparator implements Comparator<Shape>{
    SortRule sortRule;
    public OneStageComparator(SortRule sortRule){
        this.sortRule = sortRule;
    }
    public int compare(Shape s1, Shape s2){
        int asc_res = 0;
        switch (sortRule.criterion){
            case AREA -> {
                asc_res = Double.compare(s1.getArea(), s2.getArea());
            }
            case PERIMETER -> {
                asc_res = Double.compare(s1.getPerimeter(), s2.getPerimeter());
            }
            case DATE -> {
                asc_res = s1.getDateTime().compareTo(s2.getDateTime());
            }
            case VERTICES_NUMBER -> {
                asc_res = Integer.compare(s1.getVerticesNumber(), s2.getVerticesNumber());
            }
        }
        if (sortRule.order == Order.DESC)
            asc_res *= -1;
        return asc_res;
    }
}

class AreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape s1, Shape s2) {
        return Double.compare(s1.getArea(), s2.getArea());
    }
}

class PerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape s1, Shape s2) {
        return Double.compare(s1.getPerimeter(), s2.getPerimeter());
    }
}

class DateTimeComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape s1, Shape s2) {
        return s1.getDateTime().compareTo(s2.getDateTime());
    }
}