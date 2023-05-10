import com.sun.source.tree.Tree;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/*
TODO:
3. za proste isProperSetOfFeatures; na tą listę starczy, ale zaraz przestanie (TODO niżej)
4. teraz można podać kilka razy 1 cechę... może tego nie chcemy?
5. romb(bok, pole): musi zachodzić pole < bok^2
6. jedno generyczne pobieranie danych (bierze poprawne kody i enumy cech, zwraca słownik... jakoś tak)
 */

public class Main {
    private static LinkedList<Shape> allShapes = new LinkedList<>();

    private static boolean takeOneShape(String input) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Scanner scan = new Scanner(System.in);
        Shape shape = null;
        Class figureClass = null;
        switch (input) {
            case "s" -> {
                figureClass = Square.class;
            }
            case "c" -> {
                figureClass = Circle.class;
            }
            case "d" -> {
                figureClass = Rhombus.class;
            }
            case "p" -> {
                figureClass = Rectangle.class;
            }
            case "tr" -> {
                figureClass = Equilateral_Triangle.class;
            }
            case "i" -> {
                figureClass = IsoscelesTriangle.class;
            }
            case "r" -> {
                figureClass = RectangularTriangle.class;
            }
            case "e"->{
                figureClass = Ellipse.class;
            }

        }
        Map<String, Double> features = new HashMap<String, Double>();

        // TODO: obsługa wyjątków!

        while (!(boolean)figureClass.getMethod("isProperSetOfFeatures", Set.class).invoke(null, features.keySet())){
            Pair<String, Double> p = DataTaker.getFromUserFeatureValuePair(scan, figureClass);
            features.put(p.first, p.second);
        }

        shape = (Shape) figureClass.getConstructor(Map.class).newInstance(features);
        allShapes.add(shape);
        System.out.println(shape);
        System.out.println();
        return true;
    }
    private static boolean addCircumcirleOfShape(Shape shape){
        System.out.println("Wybrałeś okrąg opisany na figurze: " + shape);
        try {
            Circle circle = shape.getCircumcircle();
            allShapes.add(circle);
            System.out.println(circle);
            return true;
        }
        catch (NoCircumcircleException ex){
            System.out.println("Nie da się opisać okręgu: " + ex.getMessage() + "\n");
            return true;
        }
    }
    private static boolean addModifiedShape(Shape original_shape){
        String modificationCode = DataTaker.takeFigureModification();
        Shape addedShape = null;
        switch (modificationCode){
            case "d" -> {
                addedShape = original_shape.getDoubleShape();
            }
            case "o" -> {
                try {
                    addedShape = original_shape.getCircumcircle();
                }
                catch (NoCircumcircleException ex){
                    System.out.println("Nie da się opisać okręgu: " + ex.getMessage() + "\n");
                    return true;
                }
            }
            case "x" -> {
                return true;
            }
        }
        allShapes.add(addedShape);
        System.out.println("Nowa figura: " + addedShape);
        return true;
    }

    private static boolean deleteShape(int fig){
        allShapes.remove(fig);
        System.out.println("Deleted");
        return true;
    }

    private static SortRule getDefaultSortParams(){
        return new SortRule(SortCriterion.AREA, Order.ASC);
    }

    private static void sortShapes(SortRule sortRule){
        Collections.sort(allShapes, new OneStageComparator(sortRule));
    }

    private static boolean showAllShapes(SortRule sortRule){
        sortShapes(sortRule);
        ListIterator<Shape> it = allShapes.listIterator();
        while (it.hasNext()) {
            System.out.println(it.nextIndex()+1 + " " + it.next());
        }
        String message = "Wpisz numer figury, by dodać nową na jej podstawie:\n" +
                "Wpisz U jeśli chcesz usunąć daną figurę:\n (cokolwiek innego - powrót do głównego menu)";
        Integer number = DataTaker.takeOneNumber(1, allShapes.size(), message);
        if (number == null)
            return true;
        if(number == Constants.INF){
            String mess = "Wpisz numer figury, by usunąć ją:\n" +
                    "(cokolwiek innego - powrót do głównego menu)";
            Integer num = DataTaker.takeOneNumber(1, allShapes.size(), mess);
            return deleteShape(num-1);
        }
        Shape chosen_shape = allShapes.get(number-1);
        System.out.println("Wybrałeś figurę F: "+ chosen_shape);
        return addModifiedShape(chosen_shape);
    }

    public static boolean solveOneTask() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        String taskCode = DataTaker.takeOneTaskCommand();
        switch (taskCode){
            case "x" -> {
                return false;
            }
            case "as" -> {
                SortRule sortRule = DataTaker.getSortRule();
                return showAllShapes(sortRule);
            }
            case "a" -> {
                return showAllShapes(getDefaultSortParams());
            }
            default -> {
                return takeOneShape(taskCode);
            }
        }
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException{
        while (solveOneTask()) ;
    }
}

