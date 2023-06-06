public class SortRule {
    public SortCriterion criterion;
    public Order order;

    public SortRule(SortCriterion criterion, Order order) {
        this.order = order;
        this.criterion = criterion;
    }

    public String toString() {
        return "Sortowanie po " + criterion + " " + order;

    }

}
