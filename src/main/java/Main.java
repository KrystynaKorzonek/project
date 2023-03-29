import java.util.Scanner;

public class Main {
    public static boolean solveOneTask(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Wybierz figurę (S - kwadrat, C - koło)\nX - Zakończ");
        String input = scan.nextLine().toLowerCase();
        while (!input.equals("s") && !input.equals("c") && !input.equals("x")){
            System.out.println("Nieprawidłowa wartość!");
            System.out.println("Wybierz figurę (S - kwadrat, C - koło)\nX - Zakończ");
            input = scan.nextLine().toLowerCase();
        }

        if (input.equals("x")){
            return false;
        }
        System.out.println("Wybierz cechę, którą podasz:");
        Shape shape = null;
        if (input.equals("s")){
            System.out.println(Square.getMessageChoiceFeature());
            String feature = scan.nextLine().toLowerCase();
            while (!Square.isFeatureCode(feature)) {
                System.out.println("Nieprawidłowa wartość");
                System.out.println("Wybierz cechę, którą podasz:");
                System.out.println(Square.getMessageChoiceFeature());
                feature = scan.nextLine().toLowerCase();
            }
            System.out.println("Podaj jej wartość:");
            boolean done = false;
            double value;
            String string_value;
            while (!done) {
                string_value = scan.nextLine();
                try {
                    value = Double.parseDouble(string_value);

                    try {
                        shape = new Square(feature, value);
                        done = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage() + "\nSpróbuj jeszcze raz:");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Błąd input nie jest liczbę!" + "\nSpróbuj jeszcze raz");
                }
            }

        }
        else{ //input == "c"
            System.out.println(Circle.getMessageChoiceFeature());
            String feature = scan.nextLine().toLowerCase();
            while (!Circle.isFeatureCode(feature)) {
                System.out.println("Nieprawidłowa wartość");
                System.out.println("Wybierz cechę, którą podasz:");
                System.out.println(Circle.getMessageChoiceFeature());
                feature = scan.nextLine().toLowerCase();
            }
            System.out.println("Podaj jej wartość:");
            boolean done = false;
            double value;
            String string_value;
            while (!done){
                string_value = scan.nextLine();
                try {
                    value = Double.parseDouble(string_value);

                    try {
                        shape = new Circle(feature, value);
                        done = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage() + "\nSpróbuj jeszcze raz:");
                    }
                }

                catch(NumberFormatException e){
                    System.out.println("Błąd input nie jest liczbę!" + "\nSpróbuj jeszcze raz");
                }
            }

        }
        System.out.println(shape);
        System.out.println();

        return true;
    }
    public static void main (String[] args) {
        while(solveOneTask());

    }

}
