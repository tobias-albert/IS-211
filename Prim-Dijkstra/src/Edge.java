public class Edge {
    Node from;
    Node to;
    int weight;

    public Edge (Node from, Node to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public Edge (Node from, Node to) {
        this.from = from;
        this.to = to;
        this.weight = 1;
    }

    @Override
    public String toString() {
        return String.format("{s: %s, r: %s, w: %d}", this.from, this.to, this.weight);
    }

    @Override
    public int hashCode() {
        return this.toString().toLowerCase().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!o.getClass().equals(this.getClass())) return false;
        Edge e = (Edge) o;
        return this.hashCode() == e.hashCode();
    }
}
