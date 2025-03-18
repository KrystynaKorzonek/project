package sorting;

import shapes.Shape;

import java.util.Comparator;

public class OneStageComparator implements Comparator<Shape> {
    SortRule sortRule;

    public OneStageComparator(SortRule sortRule) {
        this.sortRule = sortRule;
    }

    public int compare(Shape s1, Shape s2) {
        int asc_res = 0;
        switch (sortRule.criterion) {
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
