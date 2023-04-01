import java.lang.reflect.InvocationTargetException;
import java.util.*;

/*
TODO:
1. solveOneTask() i main() miotają wyjątkami jak wściekłe (refleksja) --> co z tym?
2. łapanie ujemnych wartości - zabrałam z Shape - refleksja rzuca innym wyjątkiem, czy chcemy go łapać?
3. za proste isProperSetOfFeatures; na tą listę starczy, ale zaraz przestanie (TODO niżej)
 */

public class Main {
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

    public static boolean solveOneTask() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Wybierz figurę (S - kwadrat, C - koło, D - romb)\nX - Zakończ"); //D-romb-diamond :), bo prostokąt chce R też
        String input = scan.nextLine().toLowerCase();
        while (!input.equals("s") && !input.equals("c") && !input.equals("d") && !input.equals("x")) {
            System.out.println("Nieprawidłowa wartość!");
            System.out.println("Wybierz figurę (S - kwadrat, C - koło)\nX - Zakończ");
            input = scan.nextLine().toLowerCase();
        }

        if (input.equals("x")) {
            return false;
        }
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
        }
        Map<String, Double> features = new HashMap<String, Double>();


        // TODO: co jak ktoś poda coś sprzecznego (u nas na razie niemożliwe); może 3 wyjścia z funkcji isProperSetOfFeatures?

        while (!(boolean)figureClass.getMethod("isProperSetOfFeatures", Set.class).invoke(null, features.keySet())){
            Pair<String, Double> p = getFromUserFeatureValuePair(scan, figureClass);
            features.put(p.first, p.second);
        }

        shape = (Shape) figureClass.getConstructor(Map.class).newInstance(features);
        System.out.println(shape);
        System.out.println();

        return true;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        while (solveOneTask()) ;

    }
}

