import java.util.HashMap;
import java.util.HashSet;

public class Node {
    String name;
    HashSet<Node> adjacencyList;
    HashMap<Edge, Integer> edgeList;
    Node parent;

    public Node (String name) {
        this.name = name;
        adjacencyList = new HashSet<>();
        edgeList = new HashMap<>();
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return this.toString().toLowerCase().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!this.getClass().equals(o.getClass())) return false;
        return this.hashCode() == o.hashCode();
    }
}
