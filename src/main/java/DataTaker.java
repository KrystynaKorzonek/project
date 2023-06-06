import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class DataTaker {
    public static Map<String, StringPair> FIGURES_MAP = Map.of(
            "s", new StringPair("kwadrat", "square"),
            "c", new StringPair("koło", "circle"),
            "d", new StringPair("romb", "rhombus"),
            "p", new StringPair("prostokąt", "rectangle"),
            "tr", new StringPair("trójkąt równoboczny", "equilateral triangle"),
            "i", new StringPair("trójkąt równoramienny", "isosceles triangle"),
            "r", new StringPair("trójkąt prostokątny", "rectangular triangle"),
            "e", new StringPair("elipsa", "ellipse"),
            "et", new StringPair("trapez równoramienny", "isosceles trapezoid"),
            "t", new StringPair("dowolny trójkąt", "any triangle")
    );
    public static Map<String, StringPair> OTHER_ACTIONS_MAP = Map.of(
            "a", new StringPair("zobacz wszystkie figury", "see all shapes"),
            "as", new StringPair("zobacz posortowane figury", "see sorted shapes"),
            "w", new StringPair("wypisz figury do pliku", "write shapes to file"),
            "z", new StringPair("ustaw dokładność zaokrąglenia", "set rounding precision"),
            "x", new StringPair("wyjście", "exit"),
            "l", new StringPair("zmień język na angielski", "switch language to polish")
    );
    public static Map<String, StringPair> TWO_STAGES_OR_NOT_MAP = Map.of(
            "n", new StringPair("nie", "no"),
            "r", new StringPair("tak (rosnąco)", "yes (ascending)"),
            "m", new StringPair("tak (malejąco)", "yes (descending)")
    );
    public static Map<String, StringPair> SORT_CRITERION_MAP = Map.of(
            "o", new StringPair("obwód", "perimeter"),
            "p", new StringPair("pole", "area"),
            "d", new StringPair("data", "date"),
            "v", new StringPair("liczba wierzchołków", "number of vertices")
    );
    public static Map<String, StringPair> SORT_ORDER_MAP = Map.of(
            "r", new StringPair("rosnąco", "ascending"),
            "m", new StringPair("malejąco", "descending")
    );
    public static Map<String, StringPair> FIGURE_MODIFICATIONS_MAP = Map.of(
            "d", new StringPair("dodaj figurę o 2 razy większym polu", "add a figure with twice the area"),
            "o", new StringPair("dodaj okrąg opisany na F", "add a circle circumscribed on F"),
            "u", new StringPair("usuń figurę", "remove the shape")
    );
    public static Map<String, StringPair> EXIT_MAP = Map.of(
            "x", new StringPair( "zrezygnuj z dodawania figury", "resign from adding the shape")
    );

    public static String takeOneTaskCommand(Language lang){
        String initial_message = "";
        switch(lang){
            case POLISH -> {
                initial_message = "Wybierz figurę:";
            }
            case ENGLISH -> {
                initial_message = "Pick figure:";
            }
        }
        return takeOneStringFromList(FIGURES_MAP, OTHER_ACTIONS_MAP, initial_message, null);
    }
    public static String takeSortCriterion(Language lang){
        String initial_message = "";
        switch(lang){
            case POLISH -> {
                initial_message = "Wybierz kryterium sortowania:";
            }
            case ENGLISH -> {
                initial_message = "Pick criteria of sorting:";
            }
        }
        return takeOneStringFromList(SORT_CRITERION_MAP, null, initial_message, null);
    }
    public static String takeSortOrder(Language lang){
        String initial_message = "";
        switch(lang){
            case POLISH -> {
                initial_message = "Wybierz sposób sortowania:";
            }
            case ENGLISH -> {
                initial_message = "Pick type of sorting:";
            }
        }
        return takeOneStringFromList(SORT_ORDER_MAP, null, initial_message, null);
    }
    public static String takeFigureModification(Language lang){
        String initial_message = "";
        switch(lang){
            case POLISH -> {
                initial_message = "Wybierz, co chcesz zrobić:";
            }
            case ENGLISH -> {
                initial_message = "Pick, what you want to do:";
            }
        }
        return takeOneStringFromList(
                FIGURE_MODIFICATIONS_MAP,
                EXIT_MAP,
                initial_message,
                null
        );
    }
    public static SortRule getSortRule(){
        SortCriterion criterion = null;
        Order order = null;
        String criterionString = takeSortCriterion(StringManager.getLanguage());
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
            case "v" -> {
                criterion = SortCriterion.VERTICES_NUMBER;
            }
        }
        String orderString = takeSortOrder(StringManager.getLanguage());
        switch (orderString) {
            case "r" -> {
                order = Order.ASC;
            }
            case "m" -> {
                order = Order.DESC;
            }
        }
        return new SortRule(criterion, order);
    }



    public static Map<String, StringPair> makeKeysUppercase(Map<String, StringPair> map){
        Map <String, StringPair> result = new HashMap<>();
        for (String key : map.keySet())
            result.put(key.toLowerCase(), map.get(key));
        return result;
    }
    public static List<String> makeListOfOptionDescriptions(Map<String, StringPair> options){
        List<String> optionDescriptions = new LinkedList<>();
        for (String code : options.keySet())
            optionDescriptions.add(code.toUpperCase() + " - " + options.get(code).get(StringManager.getLanguage()));
        return optionDescriptions;
    }

    public static String generateOptionsMessage(Map<String, StringPair> options, Map<String, StringPair> oneLineOptions){
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

    public static String takeOneStringFromList(Map<String, StringPair> optionsFromUser, Map<String, StringPair> oneLineOptionsFromUser, String initialMessage, String finalMessage){
        Scanner scan = new Scanner(System.in);
        if (optionsFromUser == null)
            optionsFromUser = Map.of();
        if (oneLineOptionsFromUser == null)
            oneLineOptionsFromUser = Map.of();
        Map <String, StringPair> simpleOptions = makeKeysUppercase(optionsFromUser);
        Map <String, StringPair> oneLineOptions = makeKeysUppercase(oneLineOptionsFromUser);
        String optionMessage = generateOptionsMessage(simpleOptions, oneLineOptions);
        Map<String, StringPair> allOptions = new HashMap<>();
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
        String result_name = allOptions.get(result_code).get(StringManager.getLanguage());
        if (finalMessage != null)
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

    public static Pair<String, Double> getFromUserFeatureValuePair(ShapeFactory factory) {
        Scanner scan = new Scanner(System.in);
        String initial_message = "";
        String wrong_value_message = "";
        String get_value_message = "";
        String minus_val = "";
        String zero_val = "";
        String once_again_message = "";
        String not_num_message = "";
        switch(StringManager.getLanguage()){
            case POLISH -> {
                initial_message = "Wybierz cechę, którą podasz:";
                wrong_value_message = "Nieprawidłowa wartość";
                get_value_message = "Podaj jej wartość:";
                minus_val = "Ujemna wartość!";
                zero_val = "Zerowa wartość!";
                once_again_message = "Spróbuj jeszcze raz:";
                not_num_message = "Błąd: input nie jest liczbą!";
            }
            case ENGLISH -> {
                initial_message = "Pick feature which you want to add:";
                wrong_value_message = "Wrong value";
                get_value_message = "Provide its value";
                minus_val = "Value lower than 0!";
                zero_val = "Value equal 0!";
                once_again_message = "Try once again:";
                not_num_message = "Error: input is not a number";
            }
        }
        System.out.println(initial_message);
        String messageChoiceFeature = factory.getMessageChoiceFeature(StringManager.getLanguage());
        //String messageChoiceFeature = factory.getMessageChoiceFeature(StringManager.getLanguage());
        System.out.println(messageChoiceFeature);
        String feature = scan.nextLine().toLowerCase();
        while (!factory.isFeatureCode(feature)){
            System.out.println(wrong_value_message);
            System.out.println(initial_message);
            System.out.println(messageChoiceFeature);
            feature = scan.nextLine().toLowerCase();
        }
        System.out.println(get_value_message);
        boolean done = false;
        double value = 0;
        String string_value;
        while (!done) {
            string_value = scan.nextLine();
            try {
                value = Double.parseDouble(string_value);
                try {
                    if (value<0)
                        throw new IllegalArgumentException(minus_val);
                    if (value==0)
                        throw new IllegalArgumentException(zero_val);
                    done = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage() + "\n" + once_again_message);
                }
            } catch (NumberFormatException e) {
                System.out.println(not_num_message + "\n" + once_again_message);
            }
        }
        return new Pair(feature, value);
    }
}
