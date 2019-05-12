import java.util.*;

public class Graph {
    HashSet<Node> nodes;
    List<Edge> edges;

    public Graph () {
        nodes = new HashSet<>();
        edges = new LinkedList<Edge>();
    }

    public void addEdgeToList(Node from, Node to, int weight) {
        nodes.add(from);
        nodes.add(to);
        from.adjacencyList.add(to);
        Edge e = new Edge(from, to, weight);
        edges.add(e);
        from.edgeList.put(e, e.weight);
        System.out.println("Added edge " + e.toString());
    }

    public void addEdgeToList(Node from, Node to) {
        addEdgeToList(from, to, 1);
    }

    public void printEdges() {
        for (Edge e : edges) {
            System.out.println(e);
        }
    }

    public int getSize() {
        return nodes.size();
    }

    public boolean isGraphConnected(int nodes) {
        return getSize() == nodes;
    }

    public LinkedHashSet<Node> traverseBreadth (Node root) {
        LinkedHashSet<Node> checked = new LinkedHashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node checking = queue.poll();
            System.out.print(checking + ", ");
            checked.add(checking);
            //do stuff with node
            for (Node child : checking.adjacencyList) {
                if (!checked.contains(child)) {
                    queue.add(child);
                }
            }
        }
        System.out.println();
        return checked;
    }

    public LinkedHashSet<Node> traverseDepth(Node root) {
        LinkedHashSet<Node> checked = new LinkedHashSet<>();
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node checking = stack.pop();
            System.out.print(checking + ", ");
            checked.add(checking);
            //do stuff with node
            for (Node child : checking.adjacencyList) {
                if (!checked.contains(child)) {
                    stack.add(child);
                }
            }
        }
        System.out.println();
        return checked;
    }


    public int depthFirstRecursion(Node root) {
        HashSet<Node> visited = new HashSet<>();
        return traverseDepthRecursion(root, visited).size();
    }

    public HashSet<Node> traverseDepthRecursion(Node root, HashSet<Node> visited) {
        visited.add(root);
        for (Node n : root.adjacencyList) {
            if (!visited.contains(n)) {
                visited.add(n);
                visited.addAll(traverseDepthRecursion(n, visited));
            }
        }
        return visited;
    }

    public HashMap<Node, Integer> dijkstraAll (Node root) {
        HashMap<Node, Integer> distances = new HashMap<>();
        HashSet<Node> checked = new HashSet<>();
        for (Node n : nodes) {
            HashMap<Node, Integer> nMap = dijkstra(root, n, distances, checked);
            for (Node n2 : nMap.keySet()) {
                int distance = nMap.get(n2);
                if (distance != Integer.MAX_VALUE) {
                    distances.put(n2, distance);
                }
            }
        }
        return distances;
    }

    public HashMap<Node, Integer> dijkstraOne (Node root, Node target) {
        return dijkstra(root, target, new HashMap<Node, Integer>(), new HashSet<Node>());
    }

    public HashMap<Node, Integer> dijkstra(Node root, Node target, HashMap<Node, Integer> lowestDistance, HashSet<Node> checked) {
        for (Node n : nodes) {
            lowestDistance.put(n, Integer.MAX_VALUE);
        }
        lowestDistance.put(root, 0);
        PriorityQueue<Edge> queue = new PriorityQueue<>(new EdgeComparator());
        queue.addAll(root.edgeList.keySet());
        boolean found = false;
        while (!queue.isEmpty()) {
            Edge nextEdge = queue.poll();
            if (found && nextEdge.weight >= lowestDistance.get(target)) {
                break;
            }
            Node from = nextEdge.from;
            Node next = nextEdge.to;
            int newDistance = lowestDistance.get(from) + nextEdge.weight;
            int oldDistance = lowestDistance.get(next);
            lowestDistance.put(next, (newDistance < oldDistance) ? newDistance : oldDistance);
            for (Edge e : next.edgeList.keySet()) {
                if (!checked.contains(e.to)) {
                    queue.add(e);
                }
            }
            checked.add(next);
            if (next.equals(target)) found = true;
        }
        System.out.printf("Distance to target: %s from root %s is %d\n", target, root, lowestDistance.get(target));
        return lowestDistance;

    }

    public int prim(Node root) {
        HashSet<Node> checked = new HashSet<>();
        PriorityQueue<Edge> queue = new PriorityQueue<>(new EdgeComparator());
        queue.addAll(root.edgeList.keySet());
        int total = 0;
        while (!queue.isEmpty()) {
            Edge nextEdge = queue.poll();
            Node next = nextEdge.to;
            total += nextEdge.weight;
            checked.add(next);
            for (Edge e : next.edgeList.keySet()) {
                if (!checked.contains(e.to)) {
                    queue.add(e);
                }
            }
        }
        return total;
    }

    public void setup() {
        Node edvin = new Node("Edvin");
        Node tobias = new Node("Tobias");
        Node jarl = new Node("Jarl");
        Node ph = new Node("Ph");
        Node robin = new Node("Robin");
        Node kristoffer = new Node("Kristoffer");
        Node elias = new Node("Elias");
        Node vegard = new Node("Vegard");
        Node øyvind = new Node("Øyvind");
        Node adrian = new Node("Adrian");
        addEdgeToList(robin, ph, 69);
        addEdgeToList(robin, tobias, 100);
        addEdgeToList(tobias, edvin, 5);
        addEdgeToList(tobias, jarl, 12);
        addEdgeToList(jarl, vegard, 1);
        addEdgeToList(ph, vegard, 200);
        addEdgeToList(ph, øyvind, 1);
        addEdgeToList(vegard, adrian, 20);
        addEdgeToList(kristoffer, elias, 1);

        for (int i = 0; i < 5; i++) {
            System.out.println();
        }

        System.out.println("Depth, checking children of: ");
        System.out.println("final checked list: " + traverseDepth(robin));

        System.out.println("Breadth, checking children of: ");
        System.out.println("final checked list: " +traverseBreadth(robin));
        System.out.println(dijkstraOne(robin, adrian));
        System.out.println(dijkstraAll(robin));
        System.out.println(prim(robin));
    }
}
