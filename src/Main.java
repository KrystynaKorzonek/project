import java.util.Scanner;

public class Main {
    public static void solveOneTask(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Wybierz figurę (S - kwadrat, C - koło)");
        String input = scan.nextLine().toLowerCase();
        while (!input.equals("s") && !input.equals("c")){
            input = scan.nextLine().toLowerCase();
        }
        System.out.println("Wybierz cechę, którą podasz:");
        Shape shape;
        // TODO: remove repeated code
        if (input.equals("s")){
            System.out.println(Square.getMessageChoiceFeature());
            String feature = scan.nextLine().toLowerCase();
            while (!Square.isFeatureCode(feature))
                feature = scan.nextLine().toLowerCase();
            System.out.println("Podaj jej wartość:");
            double value = scan.nextDouble();
            // TODO:
            // if value < 0 ...
            shape = new Square(feature, value);
        }
        else{ //input == "c"
            System.out.println(Circle.getMessageChoiceFeature());
            String feature = scan.nextLine().toLowerCase();
            while (!Circle.isFeatureCode(feature))
                feature = scan.nextLine().toLowerCase();
            System.out.println("Podaj jej wartość:");
            double value = scan.nextDouble();
            // TODO:
            // if value < 0 ...
            shape = new Circle(feature, value);

        }
        System.out.println(shape.toString());
        System.out.println();


    }
    public static void main (String[] args) {
        System.out.println("hello");
        while(true)
            solveOneTask();
    }

}
