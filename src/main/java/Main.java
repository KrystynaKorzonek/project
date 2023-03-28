import java.util.Scanner;

public class Main {
    public static boolean solveOneTask(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Wybierz figurę (S - kwadrat, C - koło)\nX - Zakończ");
        String input = scan.nextLine().toLowerCase();
        while (!input.equals("s") && !input.equals("c") && !input.equals("x")){
            input = scan.nextLine().toLowerCase();
        }
        System.out.println("Wybierz cechę, którą podasz:");
        Shape shape = null;
        // TODO: remove repeated code

        if (input.equals("x")){
            return false;
        }
        if (input.equals("s")){
            System.out.println(Square.getMessageChoiceFeature());
            String feature = scan.nextLine().toLowerCase();
            while (!Square.isFeatureCode(feature))
                feature = scan.nextLine().toLowerCase();
            System.out.println("Podaj jej wartość:");
            boolean done = false;
            double value;
            while (!done){
                value = scan.nextDouble();
                try {
                    shape = new Square(feature, value);
                    done = true;
                }
                catch (IllegalArgumentException e){
                    System.out.println(e.getMessage() + "\nSpróbuj jeszcze raz:");
                }
            }

        }
        else{ //input == "c"
            System.out.println(Circle.getMessageChoiceFeature());
            String feature = scan.nextLine().toLowerCase();
            while (!Circle.isFeatureCode(feature))
                feature = scan.nextLine().toLowerCase();
            System.out.println("Podaj jej wartość:");
            boolean done = false;
            double value;
            while (!done){
                value = scan.nextDouble();
                try {
                    shape = new Circle(feature, value);
                    done = true;
                }
                catch (IllegalArgumentException e){
                    System.out.println(e.getMessage() + "\nSpróbuj jeszcze raz:");
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
