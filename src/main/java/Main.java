import java.io.IOException;
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
    private static TreeSet<Shape> allShapes = new TreeSet<>();
    //private static LinkedList<Shape> allShapes = new LinkedList<>();
    private static boolean addToAllShapes(Shape s){
        if (s!= null){
            if (allShapes.contains(s)){
                System.out.println(StringManager.getMessageString(Message.NOT_ADDED_ALREADY_EXISTS));
            }
            else {
                allShapes.add(s);
                //System.out.println(StringManager.getMessageString(Message.ADDED) + s.toString(StringManager.getLanguage()); //todo!
                System.out.print(StringManager.getMessageString(Message.ADDED) + StringManager.wrapToString(s));
            }
            return true;
        }
        System.out.println(StringManager.getMessageString(Message.FAILED_ADD));
        return false;
    }
    private static boolean takeOneShape(String input) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException{
        Shape shape = null;
        ShapeFactory factory = null;
        switch (input) {
            case "s" -> {
                factory = new SquareFactory();
            }
            case "c" -> {
                factory = new CircleFactory();
            }
            case "d" -> {
                factory = new RhombusFactory();
            }
            case "p" -> {
                factory = new RectangleFactory();
            }
            case "tr" -> {
                factory = new EquilateralTriangleFactory();
            }
            case "i" -> {
                factory = new IsoscelesTriangleFactory();
            }
            case "r" -> {
                factory = new RectangularTriangleFactory();
            }
            case "e"->{
                factory = new EllipseFactory();
            }
            case "et"->{
                factory = new EquilateralTrapezoidFactory();
            }
            case "t"->{
                factory = new TriangleFactory();
            }
        }
        Map<String, Double> features = new HashMap<String, Double>();

        while (!factory.isProperSetOfFeatures(features.keySet())){
            Pair<String, Double> p = DataTaker.getFromUserFeatureValuePair(factory);
            features.put(p.first, p.second);
        }

        try {
            shape = factory.create(features);
            addToAllShapes(shape);
            System.out.println();
            return true;
        }
        catch (IllegalArgumentException ex){
            System.out.println(StringManager.getMessageString(Message.IMPOSSIBLE_CREATE) + ": " + ex.getMessage() + "\n");
            return true;
        }
        catch (NoSuchTriangleException ex){
            System.out.println(StringManager.getMessageString(Message.IMPOSSIBLE_CREATE) + ": " + ex.getMessage() + "\n");
            return true;
        }
        catch (NoCircumcircleException ex){
            return true;
        }

    }
    private static boolean addCircumcirleOfShape(Shape shape){
        System.out.println(StringManager.getMessageString(Message.CIRCUMCIRCLE_CHOSEN) + StringManager.wrapToString(shape));
        try {
            Circle circle = shape.getCircumcircle();
            addToAllShapes(circle);
            return true;
        }
        catch (NoCircumcircleException ex){
            System.out.println(StringManager.getMessageString(Message.IMPOSSIBLE_CIRCUMCIRCLE) + ex.getMessage() + "\n");
            return true;
        }
    }
    private static boolean addModifiedShape(Shape originalShape, String modificationCode){
        Shape addedShape = null;
        switch(modificationCode){
            case "d" -> {
                addedShape = originalShape.getDoubleShape();
            }
            case "o" -> {
                try {
                    addedShape = originalShape.getCircumcircle();
                }
                catch (NoCircumcircleException ex){
                    System.out.println(StringManager.getMessageString(Message.IMPOSSIBLE_CIRCUMCIRCLE) + ex.getMessage() + "\n");
                    return true;
                }
            }
        }
        addToAllShapes(addedShape);
        return true;
    }
    private static boolean doActionOnShape(Shape chosenShape){
        System.out.println(StringManager.getMessageString(Message.CHOSEN_F) + StringManager.wrapToString(chosenShape));
        String modificationCode = DataTaker.takeFigureModification(StringManager.getLanguage());
        switch (modificationCode){
            case "d" -> {
                return addModifiedShape(chosenShape, "d");
            }
            case "o" -> {
                return addModifiedShape(chosenShape, "o");
            }
            case "u" -> {
                return deleteShape(chosenShape);
            }
            case "x" -> {
                return true;
            }
        }
        return true;
    }

    private static boolean deleteShape(Shape chosenShape){
        allShapes.remove(chosenShape);
        System.out.println(StringManager.getMessageString(Message.DELETED));
        return true;
    }

    private static SortRule getDefaultSortParams(){
        return new SortRule(SortCriterion.AREA, Order.ASC);
    }


    private static boolean showAllShapes(SortRule sortRule){
        if (allShapes.size() == 0)
            return true;
        LinkedList<Shape> sortedShapes = new LinkedList<>();
        sortedShapes.addAll(allShapes);
        Collections.sort(sortedShapes, new OneStageComparator(sortRule));
        ListIterator<Shape> it = sortedShapes.listIterator();
        while (it.hasNext()) {
            System.out.println(it.nextIndex()+1 + " " + StringManager.wrapToString(it.next()));
        }
        String message = StringManager.getMessageString(Message.CHOOSE_NUMBER_OF_SHAPE);
        Integer number = DataTaker.takeOneNumber(1, allShapes.size(), message);
        if (number == null)
            return true;
        Shape chosen_shape = sortedShapes.get(number-1);
        return doActionOnShape(chosen_shape);
    }

    private static boolean writeAllShapes() throws IOException {
        MultithreadingWriteToFile.write_file_async(allShapes);
        System.out.println(StringManager.getMessageString(Message.WRITTEN_TO_FILE));
        return true;
    }

    private static boolean changeRoundPlaces(){
        String initial = StringManager.getMessageString(Message.CHOOSE_ROUND) + " (min " +
                Constants.MIN_ROUND_PLACES + ", max "  + Constants.MAX_ROUND_PLACES + ")";
        Integer places = DataTaker.takeOneNumber(Constants.MIN_ROUND_PLACES, Constants.MAX_ROUND_PLACES, initial);
        if (places == null){
            System.out.println(StringManager.getMessageString(Message.ROUND_INCORRECT));
        }
        else{
            System.out.println(StringManager.getMessageString(Message.ROUND_CHANGED) + places);
            RoundClass.setRoundPlaces(places);
        }

       return true;
    }
    private static boolean changeLanguage(){
        StringManager.changeLanguage();
        return true;
    }

    public static boolean solveOneTask() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, IOException {
        String taskCode = DataTaker.takeOneTaskCommand(StringManager.getLanguage());
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
            case "w" -> {
                return writeAllShapes();
            }
            case "z" -> {
                return changeRoundPlaces();
            }
            case "l" -> {
                return changeLanguage();
            }
            default -> {
                return takeOneShape(taskCode);
            }
        }
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, IOException {
        while (solveOneTask()) ;
    }
}

