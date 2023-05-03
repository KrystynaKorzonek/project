public class SortRule {
    public boolean verticesStage;
    public Order orderVerticesStage;
    public SortCriterion criterion;
    public Order order;

    public SortRule(boolean verticesStage, Order orderVerticesStage,  SortCriterion criterion, Order order){
        this.verticesStage = verticesStage;
        this.orderVerticesStage = orderVerticesStage;
        this.order = order;
        this.criterion = criterion;
    }
    // ? można pomijając kolejność pierwszego sortowania
    // co ma sens tylko gdy verticesStage = false
    public SortRule(boolean verticesStage, SortCriterion criterion, Order order){
        this.verticesStage = verticesStage;
        this.order = order;
        this.criterion = criterion;
    }

    public String toString(){
        //TODO: ładne polskie wypisywanie
        if (verticesStage){
            return "Sortowanie dwupoziomowe: 1. po wierzchołkach " + orderVerticesStage +
                    " 2. po " + criterion + " " + order;
        }
        else{
            return "Sortowanie po " + criterion + " " + order;
        }
    }

}
