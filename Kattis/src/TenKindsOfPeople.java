import java.util.*;

public class TenKindsOfPeople {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int height = sc.nextInt();

        ArrayList<String> graph = new ArrayList<>();

        sc.nextLine();
        for (int i = 0; i < height; i++) {
            graph.add(sc.nextLine());
        }

        int amountOfQueries = sc.nextInt();

        BSF bsf = new BSF(graph);

        int[][] queries = new int[amountOfQueries][4];

        for (int i = 0; i < queries.length; i++) {
            queries[i][0] = sc.nextInt() - 1;
            queries[i][1] = sc.nextInt() - 1;
            queries[i][2] = sc.nextInt() - 1;
            queries[i][3] = sc.nextInt() - 1;
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < queries.length; i++) {
            System.out.println(bsf.getSearchResult(queries[i][0], queries[i][1], queries[i][2], queries[i][3]));
        }
        System.out.println("Time: " + ((System.currentTimeMillis() - start) / 1000f));

    }
}

class BSF {

    private PriorityQueue<GraphNode> queue;
    private HashMap<String, Float> weightedNodes;
    private ArrayList<String> graph;
    private int walkableNodeValue;
    private int destX, destY;

    public BSF(ArrayList<String> graph) {
        this.graph = graph;
        prepareNextSearch();
    }

    private void prepareNextSearch() {
        weightedNodes = new HashMap<>();
        queue = new PriorityQueue<>(new NodeComparator(weightedNodes));
    }

    public String getSearchResult(int x1, int y1, int x2, int y2) {
        destX = x2;
        destY = y2;
        walkableNodeValue = valueOfNode(x1, y1);

        if (x1 == x2 && y1 == y2) {
            return resultText();
        }
        if (!isWithinGraph(x2, y2)) {
            return "neither";
        }

        if (walkableNodeValue != valueOfNode(x2, y2)) {
            return "neither";
        }

        addToQueue(x1, y1);

//        int count = 1;
        while (!queue.isEmpty()) {
            GraphNode node = queue.remove();

            if (isDestination(node, x2, y2)) {
//                System.out.println(count);
                prepareNextSearch();
                return (resultText());
            }

            addNeighbours(node);
//            count++;
        }
        prepareNextSearch();
        return "neither";
    }

    private String resultText() {
        if (walkableNodeValue == 0) return "binary";
        return "decimal";
    }

    private boolean isDestination(GraphNode node, int x, int y) {
        return node.getX() == x && node.getY() == y;

    }

    private void addNeighbours(GraphNode node) {
        if (isWithinGraph(node.getX() + 1, node.getY())) {
            addToQueue(node.getX() + 1, node.getY());
        }
        if (isWithinGraph(node.getX(), node.getY() + 1)) {
            addToQueue(node.getX(), node.getY() + 1);
        }
        if (isWithinGraph(node.getX() - 1, node.getY())) {
            addToQueue(node.getX() - 1, node.getY());
        }
        if (isWithinGraph(node.getX(), node.getY() - 1)) {
            addToQueue(node.getX(), node.getY() - 1);
        }
    }

    private void addToQueue(int x, int y) {
        if (valueOfNode(x, y) != walkableNodeValue) return;

        GraphNode node = new GraphNode(x, y);
        if (weightedNodes.containsKey(node.getValue())) {
            return;
        }
        weightedNodes.put(node.getValue(), h(node));
        queue.add(node);
    }

    private float h(GraphNode node) {
        return (float) Math.sqrt(Math.pow(node.getX() - destX, 2) + Math.pow(node.getY() - destY, 2));
    }

    private int valueOfNode(int x, int y) {
        return Character.getNumericValue(graph.get(x).charAt(y));
    }

    private boolean isWithinGraph(int x, int y) {
        if (x >= 0 && x < graph.size()) {
            if (y >= 0 && y < graph.get(x).length()) {
                return true;
            }
        }
        return false;
    }
}

class NodeComparator implements Comparator<GraphNode> {

    HashMap<String, Float> weightedNodes;

    public NodeComparator(HashMap<String, Float> weightedNodes) {
        this.weightedNodes = weightedNodes;
    }

    @Override
    public int compare(GraphNode n1, GraphNode n2) {
        if (weightedNodes.get(n1.getValue()) < weightedNodes.get(n2.getValue())) return -1;
        if (weightedNodes.get(n1.getValue()) > weightedNodes.get(n2.getValue())) return 1;
        return 0;
    }
}

class GraphNode {
    private int x, y;
    private String value;

    public GraphNode(int x, int y) {
        this.x = x;
        this.y = y;
        value = x + "," + y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getValue() {
        return value;
    }
}

