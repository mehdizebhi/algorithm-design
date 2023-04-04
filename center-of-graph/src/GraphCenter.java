
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GraphCenter {

    static Scanner in = new Scanner(System.in);
    private Map<Integer, Node> nodes = new HashMap<>();   // nodes are actually graphs
    private int n;       // |V|
    private int m;       // |E|
    private int centralNodeOfTheGraph;                    // output
    private List<List<Node>> Previous;

    public void run() {
        input();
        Previous = new ArrayList<>();
        Previous.add(0, null);
        for (int i = 0; i < this.n; i++) {
            Previous.add(i + 1, dijkstra(i + 1));
        }
        centerOfGraph();

        System.out.println(centralNodeOfTheGraph);
    }

    /*
    We place all found paths in a string and then get the highest number of nodes in the string for each node.
    In fact, the highest number of nodes in the same string is the highest number of nodes in all the shortest paths.
     */
    private void centerOfGraph() {

        String allShortenPath = "";
        for (int i = 1; i < this.n; i++) {
            for (int j = i + 1; j <= n; j++) {
                allShortenPath += pre(i, j);
            }
        }

        int max = 0;
        char ch;
        int label = 0;
        for (int i = 1; i <= this.n; i++) {
            ch = Character.forDigit(i, 10);
            if (max < searchNumberOfcharInString(allShortenPath, ch)) {
                max = searchNumberOfcharInString(allShortenPath, ch);
                label = i;
            }
        }

        centralNodeOfTheGraph = label;

    }

    private int searchNumberOfcharInString(String str, char ch) {
        int num = 0;
        for (int i = 0; i < str.length(); i++) {
            if (ch == str.charAt(i)) {
                num++;
            }
        }
        return num;
    }

    //Gains the shortest found path in Dijkstra algorithm through recursive function
    private String pre(int start, int end) {
        if (start == end || Previous.get(start).get(end) == null) {
            return String.valueOf(start);
        }
        int label = Previous.get(start).get(end).label;
        if (label == 0) {
            return String.valueOf(start);
        }
        return pre(start, label) + String.valueOf(end);
    }

    private List<Node> dijkstra(int start) {

        List<Node> previous = new ArrayList<>();
        for (int i = 0; i <= this.n; i++) {
            previous.add(i, null);
        }
        previous.add(start, new Node(0, 0, null));

        double[] distance = new double[n + 1];
        Arrays.fill(distance, Double.POSITIVE_INFINITY);
        distance[start] = 0;

        Map<Integer, Node> nodesCopy = new HashMap<>();
        nodesCopy = copyGraph();

        while (!nodesCopy.isEmpty()) {

            Node v = extractMin_GreedyChoice(distance, nodesCopy);
            int vLable = v.label;

            for (Edge edge : v.edges) {
                Node u = edge.connectedTo;
                int uLable = u.label;
                if (distance[vLable] + edge.weight < distance[uLable]) {
                    distance[uLable] = distance[vLable] + edge.weight;
                    previous.add(uLable, v);
                }
            }
        }
        return previous;
    }

    // extractMin GreedyChoice for Dijkstra algorithm
    private Node extractMin_GreedyChoice(double[] distance, Map<Integer, Node> nodesCopy) {
        double min = Double.POSITIVE_INFINITY;
        int label = 0;
        Node node = null;
        for (int i = 1; i <= n; i++) {
            if (min > distance[i] && nodesCopy.get(i) != null) {
                min = distance[i];
                label = i;
            }
        }
        node = nodesCopy.remove(label);
        return node;
    }

    private Map<Integer, Node> copyGraph() {

        Map<Integer, Node> nodesCopy = new HashMap<>();

        for (int label : nodes.keySet()) {
            nodesCopy.put(label, nodes.get(label));
        }
        return nodesCopy;
    }

    private class Node {

        public int label;
        public double value;
        public List<Edge> edges;

        public Node(int label, double value, List<Edge> edges) {
            this.label = label;
            this.value = value;
            this.edges = edges;
        }

    }

    private class Edge {

        public double weight;
        public Node connectedTo;

        public Edge(double weight, Node connectedTo) {
            this.weight = weight;
            this.connectedTo = connectedTo;
        }
    }

    // create graph with inputs
    private void input() {
        String[] nodeEdge = in.nextLine().split(" ");
        this.n = Integer.parseInt(nodeEdge[0]);
        this.m = Integer.parseInt(nodeEdge[1]);

        for (int i = 0; i < this.n; i++) {
            List<Edge> edges = new ArrayList<>();
            Node node = new Node(i + 1, 0, edges);
            nodes.put(i + 1, node);
        }

        for (int i = 0; i < this.m; i++) {
            String[] nodesWeight = in.nextLine().split(" ");

            int vLabel = Integer.parseInt(nodesWeight[0]);
            int uLabel = Integer.parseInt(nodesWeight[1]);
            double weight = Integer.parseInt(nodesWeight[2]);

            Node v = nodes.get(vLabel);
            Node u = nodes.get(uLabel);

            Edge edgeV = new Edge(weight, u);
            Edge edgeU = new Edge(weight, v);

            v.edges.add(edgeV);
            u.edges.add(edgeU);

            nodes.replace(vLabel, v);
            nodes.replace(uLabel, u);
        }

    }

    public static void main(String[] args) {
        GraphCenter test = new GraphCenter();
        test.run();
    }
}
