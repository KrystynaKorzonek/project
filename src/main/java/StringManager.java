import java.util.HashMap;
import java.util.Map;

public class StringManager {
    private static Language lang = Language.POLISH;
    private static void add(Message m, String pl, String en){
        strings.put(m, new StringPair(pl, en));
    }
    private static Map<Message, StringPair> strings = new HashMap<>();

    static{
        // UŻYWANE W MAINIE:
        add(Message.FAILED_ADD, "Nie udało się dodać figury", "Failed to add shape");
        add(Message.IMPOSSIBLE_CREATE, "Nie da sie stworzyć figury", "Impossible to create shape");
        add(Message.ADDED, "Dodano figurę: ", "Shape added: ");
        add(Message.DELETED, "Usunięto figurę", "Shape deleted");
        add(Message.WRITTEN_TO_FILE, "Zapisano do pliku", "Written to a file");
        add(Message.CIRCUMCIRCLE_CHOSEN, "Wybrałeś okrąg opisany na figurze: ", "You've chosen circumcircle of: ");
        add(Message.IMPOSSIBLE_CIRCUMCIRCLE, "Nie da się opisać okręgu: ", "There is no circumcircle: ");
        add(Message.CHOSEN_F, "Wybrałeś figurę F: ", "You've chosen shape F: ");
        add(Message.CHOOSE_NUMBER_OF_SHAPE,
                "Wpisz numer figury, by wykonać akcję (usunąć ją lub dodać nową na jej podstawie):\n" +
                        "(cokolwiek innego - powrót do głównego menu)",
                    "Enter number of shape to perform action on it (delete or add new based on it):\n" +
                            "(anything else - return to main menu"
                );
        add(Message.CHOOSE_ROUND,
                "Wybierz, do ilu cyfr po przecinku zaokraglać wartości",
                "Choose number of decimal places in rounding"
        );
        add(Message.ROUND_CHANGED, "Nowa dokładność zaokrąglania: ", "New round precision: ");
        add(Message.ROUND_INCORRECT,
                "Niepoprawna wartość. Nie wprowadzono zmian.",
                "Incorrect value. No changes applied."
        );
        // UŻYWANE W DATATAKER

        //TODO: Stringi z DataTakera
        // z funkcji:
        // 1. 4 funkcje na początku (takeSth...), wrappery na takeOneStringFromList
        // 2.takeOneStringFromList
        // 3.getFromUserFeatureValuePair
        // koniecznie zostawić komentarze, skąd jest ten String (to i tak jest dosć chaotyczne)
    }


    public static void setLanguage(Language l){
        lang = l;
    }
    public static Language getLanguage(){
        return lang;
    }
    public static void changeLanguage(){
        switch (lang){
            case POLISH -> {
                setLanguage(Language.ENGLISH);
            }
            case ENGLISH -> {
                setLanguage(Language.POLISH);
            }
        }
    }

    public static String getMessageString(Message m){
        return strings.get(m).get(lang);
    }

}
