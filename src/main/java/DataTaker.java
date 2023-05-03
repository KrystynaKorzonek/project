import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class DataTaker {
    public static Map<String, String> FIGURES_MAP = Map.of(
            "s", "kwadrat",
            "c", "koło",
            "d", "romb",
            "p", "prostokąt",
            "tr", "trójkąt równoboczny",
            "i", "trójkąt równoramienny",
            "r", "trójkąt prostokątny",
            "e", "elipsa"
    );
    public static Map<String, String> OTHER_ACTIONS_MAP = Map.of(
            "a", "zobacz wszystkie figury",
            "as", "zobacz posortowane figury",
            "x", "wyjście"
    );
    public static Map<String, String> TWO_STAGES_OR_NOT_MAP = Map.of(
            "n", "nie",
            "r", "tak (rosnąco)",
            "m", "tak (malejąco)"
    );
    public static Map<String, String> SORT_CRITERION_MAP = Map.of(
            "o", "obwód",
            "p", "pole",
            "d", "data"
    );
    public static Map<String, String> SORT_ORDER_MAP = Map.of(
            "r", "rosnąco",
            "m", "malejąco"
    );
    public static Map<String, String> FIGURE_MODIFICATIONS_MAP = Map.of(
            "d", "figura o 2 razy większym polu",
            "o", "okrąg opisany na F"
    );
    public static Map<String, String> EXIT_MAP = Map.of(
            "x", "zrezygnuj z dodawania figury"
    );

    public static String takeOneTaskCommand(){
        return takeOneStringFromList(FIGURES_MAP, OTHER_ACTIONS_MAP, "Wybierz figurę:", null);
    }
    public static String takeTwoStagesOrNotCommand(){
        return takeOneStringFromList(TWO_STAGES_OR_NOT_MAP, null, "Czy sortować dwupoziomowo (pierwszy poziom - liczba wierzchołków)", null);
    }
    public static String takeSortCriterion(){
        return takeOneStringFromList(SORT_CRITERION_MAP, null, "Wybierz kryterium sortowania:", null);
    }
    public static String takeSortOrder(){
        return takeOneStringFromList(SORT_ORDER_MAP, null, "Wybierz sposób sortowania:", null);
    }
    public static String takeFigureModification(){
        return takeOneStringFromList(
                FIGURE_MODIFICATIONS_MAP,
                EXIT_MAP,
                "Wybierz, jaką figurę chcesz dodać:",
                null
        );
    }
    public static SortRule getSortRule(){
        String twoStagesOrNot = takeTwoStagesOrNotCommand();
        boolean twoStages = true;
        Order verticesOrder = null;
        SortCriterion criterion = null;
        Order order = null;

        switch (twoStagesOrNot){
            case "n" -> {
                twoStages = false;
            }
            case "r" -> {
                verticesOrder = Order.ASC;
            }
            case "m" -> {
                verticesOrder = Order.DESC;
            }
        }
        String criterionString = takeSortCriterion();
        switch (criterionString){
            case "o" -> {
                criterion = SortCriterion.PERIMETER;
            }
            case "p" -> {
                criterion = SortCriterion.AREA;
            }
            case "d" -> {
                criterion = SortCriterion.DATE;
            }
        }
        String orderString = takeSortOrder();
        switch (orderString) {
            case "r" -> {
                order = Order.ASC;
            }
            case "m" -> {
                order = Order.DESC;
            }
        }
        return new SortRule(twoStages, verticesOrder, criterion, order);
    }



    public static Map<String, String> makeKeysUppercase(Map<String, String> map){
        Map <String, String> result = new HashMap<>();
        for (String key : map.keySet())
            result.put(key.toLowerCase(), map.get(key));
        return result;
    }
    public static List<String> makeListOfOptionDescriptions(Map<String, String> options){
        List<String> optionDescriptions = new LinkedList<>();
        for (String code : options.keySet())
            optionDescriptions.add(code.toUpperCase() + " - " + options.get(code));
        return optionDescriptions;
    }

    public static String generateOptionsMessage(Map<String, String> options, Map<String, String> oneLineOptions){
        List<String> optionsDescriptions = makeListOfOptionDescriptions(options);
        List<String> oneLineOptionsDescritions = makeListOfOptionDescriptions(oneLineOptions);
        String firstLineOptionMessage = String.join(", ", optionsDescriptions);
        String oneLineOptionMessage = String.join("\n", oneLineOptionsDescritions);
        String delimiter = "\n";
        if (optionsDescriptions.isEmpty() || oneLineOptionsDescritions.isEmpty())
            delimiter = "";
        String optionMessage = firstLineOptionMessage + delimiter + oneLineOptionMessage;
        return(optionMessage);
    }

    public static String takeOneStringFromList(Map<String, String> optionsFromUser, Map<String, String> oneLineOptionsFromUser, String initialMessage, String finalMessage){
        Scanner scan = new Scanner(System.in);
        if (optionsFromUser == null)
            optionsFromUser = Map.of();
        if (oneLineOptionsFromUser == null)
            oneLineOptionsFromUser = Map.of();
        Map <String, String> simpleOptions = makeKeysUppercase(optionsFromUser);
        Map <String, String> oneLineOptions = makeKeysUppercase(oneLineOptionsFromUser);
        String optionMessage = generateOptionsMessage(simpleOptions, oneLineOptions);
        Map<String, String> allOptions = new HashMap<>();
        allOptions.putAll(simpleOptions);
        allOptions.putAll(oneLineOptions);

        if (initialMessage != null)
            System.out.println(initialMessage);
        else
            System.out.println("Wybierz jedną z poniższych opcji:");
        System.out.println(optionMessage);
        String input = scan.nextLine().toLowerCase();
        while (!allOptions.containsKey(input)) {
            System.out.println("Nieprawidłowa wartość! Spróbuj jeszcze raz: ");
            System.out.println(optionMessage);
            input = scan.nextLine().toLowerCase();
        }
        String result_code = input;
        String result_name = allOptions.get(result_code);
        if (finalMessage != null) //nie zawsze chcemy informować usera, że coś wybrał; czasem byłoby za dużo komuniaktów
            System.out.println(finalMessage + result_name);
        return result_code;
    }


    public static Integer takeOneNumber(int min, int max, String initialMessage){
        Scanner scan = new Scanner(System.in);
        System.out.println(initialMessage);
        Integer chosen_number;
        String string_value = scan.nextLine();
        int number;
        try {
            number = Integer.parseInt(string_value);
            if (number < min || number > max)
                return null;
            chosen_number = number;
        }
        catch (NumberFormatException ex){
            return null;
        }
        return chosen_number;
    }

    // przeniesiona żywcem ze starego maina
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
}
