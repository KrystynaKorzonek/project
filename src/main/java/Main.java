import com.sun.source.tree.Tree;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/*
TODO:
1. solveOneTask() i main() miotają wyjątkami jak wściekłe (refleksja) --> co z tym?
2. łapanie ujemnych wartości - zabrałam z Shape - refleksja rzuca innym wyjątkiem, czy chcemy go łapać?
3. za proste isProperSetOfFeatures; na tą listę starczy, ale zaraz przestanie (TODO niżej)
4. teraz można podać kilka razy 1 cechę... może tego nie chcemy?
5. romb(bok, pole): musi zachodzić pole < bok^2
6. jedno generyczne pobieranie danych (bierze poprawne kody i enumy cech, zwraca słownik... jakoś tak)
 */

public class Main {
    //private static TreeSet<Shape> allShapes = new TreeSet<>(); :(
    enum Order {ASC, DESC};
    enum SortCriterion {AREA, PERIMETER};
    private static LinkedList<Shape> allShapes = new LinkedList<>();
    public static Pair<String, Double> getFromUserFeatureValuePair(Scanner scan, Class figureClass) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("Wybierz cechę, którą podasz:");
        String messageChoiceFeature = (String) figureClass.getMethod("getMessageChoiceFeature").invoke(null);
        System.out.println(messageChoiceFeature);
        String feature = scan.nextLine().toLowerCase();
        while (!(boolean)figureClass.getMethod("isFeatureCode", String.class).invoke(null, feature)) {
            System.out.println("Nieprawidłowa wartość");
            System.out.println("Wybierz cechę, którą podasz:");
            System.out.println(messageChoiceFeature);
            feature = scan.nextLine().toLowerCase();
        }
        System.out.println("Podaj jej wartość:");
        boolean done = false;
        double value = 0;
        String string_value;
        while (!done) {
            string_value = scan.nextLine();
            try {
                value = Double.parseDouble(string_value);
                try {
                    if (value<0)
                        throw new IllegalArgumentException("Ujemna wartość!");
                    if (value==0)
                        throw new IllegalArgumentException("Zerowa wartość!");
                    done = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage() + "\nSpróbuj jeszcze raz:");
                }
            } catch (NumberFormatException e) {
                System.out.println("Błąd: input nie jest liczbą!" + "\nSpróbuj jeszcze raz");
            }
        }
        return new Pair(feature, value);
    }
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

        }
        Map<String, Double> features = new HashMap<String, Double>();


        // TODO: co jak ktoś poda coś sprzecznego (u nas na razie niemożliwe); może 3 wyjścia z funkcji isProperSetOfFeatures?

        while (!(boolean)figureClass.getMethod("isProperSetOfFeatures", Set.class).invoke(null, features.keySet())){
            Pair<String, Double> p = getFromUserFeatureValuePair(scan, figureClass);
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
        Scanner scan = new Scanner(System.in);
        System.out.println("Wybierz, jaką figurę chcesz dodać (D - figura o 2 razy większym polu, O - okrąg opisany na F):\n(cokolwiek innego - powrót do głównego menu)");
        String input = scan.nextLine().toLowerCase();
        Shape addedShape;
        if (input.equals("d"))
            addedShape = original_shape.getDoubleShape();
        else if (input.equals("o"))
            try {
                addedShape = original_shape.getCircumcircle();
            }
            catch (NoCircumcircleException ex){
                System.out.println("Nie da się opisać okręgu: " + ex.getMessage() + "\n");
                return true;
            }
        else
            return true;
        allShapes.add(addedShape);
        System.out.println("Nowa figura: " + addedShape);
        return true;
    }


    private static Pair<Order, SortCriterion> getSortParams(){
        Order order = null;
        SortCriterion criterion = null;
        Scanner scan = new Scanner(System.in);
        String messageChoiceCriterion = "Wybierz kryterium sortowania (O-obwód, P-pole)";
        String messageChoiceOrder = "Wybierz sposób sortowania (R-rosnąco, M-malejąco)";
        System.out.println(messageChoiceCriterion);
        String input = scan.nextLine().toLowerCase();
        while (!input.equals("o") && !input.equals("p")) {
            System.out.println("Nieprawidłowa wartość!");
            System.out.println(messageChoiceCriterion);
            input = scan.nextLine().toLowerCase();
        }
        switch (input){
            case "o" -> {
                criterion = SortCriterion.PERIMETER;
            }
            case "p" -> {
                criterion = SortCriterion.AREA;
            }
        }
        System.out.println(messageChoiceOrder);
        input = scan.nextLine().toLowerCase();
        while (!input.equals("r") && !input.equals("m")) {
            System.out.println("Nieprawidłowa wartość!");
            System.out.println(messageChoiceCriterion);
            input = scan.nextLine().toLowerCase();
        }
        switch (input){
            case "r" -> {
                order = Order.ASC;
            }
            case "m" -> {
                order = Order.DESC;
            }
        }
        return new Pair<Order, SortCriterion>(order, criterion);

    }
    private static boolean showAllShapes(Pair<Order, SortCriterion> sortParams){
        Order order = sortParams.first;
        SortCriterion criterion = sortParams.second;
        switch (criterion){
            case AREA -> {
                allShapes.sort(Comparator.comparing(Shape::getArea));
            }
            case PERIMETER -> {
                allShapes.sort(Comparator.comparing(Shape::getPerimeter));
            }
        }
        if (order == Order.DESC){
            Collections.reverse(allShapes);
        }
        ListIterator<Shape> it = allShapes.listIterator();
        while (it.hasNext()) {
            System.out.println(it.nextIndex()+1 + " " + it.next());
        }


        System.out.println("Wpisz numer figury, by dodać nową na jej podstawie:\n(cokolwiek innego - powrót do głównego menu)");
        Scanner scan = new Scanner(System.in);
        String string_value = scan.nextLine();
        int number;
        Shape chosen_shape;
        try {
            number = Integer.parseInt(string_value);
            if (number <= 0 || number > allShapes.size())
                return true;
            chosen_shape = allShapes.get(number-1);
        }
        catch (NumberFormatException ex){
            return true;
        }
        System.out.println("Wybrałeś figurę F: "+ chosen_shape);
        return addModifiedShape(chosen_shape);



        //return addCircumcirleOfShape(chosen_shape);

    }

    public static boolean solveOneTask() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Scanner scan = new Scanner(System.in);
        String messageChoiceShape = "Wybierz figurę (S - kwadrat, C - koło, D - romb, P - prostokat, TR - trojkat rownoboczny, I - trójkąt równoramienny R - trójkąt prostokątny)\nA - zobacz wszystkie figury\nX - Zakończ";
        System.out.println(messageChoiceShape);
        String input = scan.nextLine().toLowerCase();
        while (!input.equals("s") && !input.equals("c") && !input.equals("d")  && !input.equals("p") && !input.equals("x") && !input.equals("a") && !input.equals("tr") && !input.equals("i") && !input.equals("r")) {
            System.out.println("Nieprawidłowa wartość!");
            System.out.println(messageChoiceShape);
            input = scan.nextLine().toLowerCase();
        }
        if (input.equals("x")) {
            return false;
        }
        if (input.equals("a")){
            Pair<Order, SortCriterion> sortParams = getSortParams();
            return showAllShapes(sortParams);
        }
        else{
            return takeOneShape(input);
        }
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        while (solveOneTask()) ;

    }
}

