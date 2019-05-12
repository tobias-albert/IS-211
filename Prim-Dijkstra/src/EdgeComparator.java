import java.util.Comparator;

public class EdgeComparator implements Comparator<Edge> {

    public EdgeComparator() {

    }

    @Override
    public int compare (Edge e1, Edge e2) {
        return e1.weight - e2.weight;
    }
}
