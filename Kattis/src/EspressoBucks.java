import java.util.Scanner;

public class EspressoBucks {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] graph = new char[sc.nextInt()][sc.nextInt()];

        sc.nextLine();

        for (int i = 0; i < graph.length; i++) {
            graph[i] = sc.nextLine().toCharArray();
        }

        new EspressoPlanner(graph);

    }
}

class EspressoPlanner {
    private char[][] graph;
    private final Character FREESPACE = '.', ADJACENT = 'A';

    public EspressoPlanner(char[][] graph) {
        this.graph = graph;
        calcOptimizedShopPositioning();
        show();
    }

    private void calcOptimizedShopPositioning() {
        for (int i = 0; i < graph.length; i++) {

            for (int j = 0; j < graph[i].length; j++) {

                if (isValidPosition(i, j)) {
                    if (isPositionLeftValid(i, j)) {
                        //Place here
                        placeEspressoHouseAtPosition(i, j);

                    } else if (isPositionRightValid(i, j)) {
                        //place right
                        placeEspressoHouseRight(i, j);

                    } else if (isPositionBelowValid(i, j)) {
                        placeEspressoHouseBelow(i, j);
                    } else {
                        placeEspressoHouseAtPosition(i, j);
                    }

                }
            }
        }
    }

    private void placeEspressoHouseBelow(int i, int j) {
        placeEspressoHouseAtPosition(i + 1, j);
    }

    private void placeEspressoHouseRight(int i, int j) {
        placeEspressoHouseAtPosition(i, j + 1);
    }

    private void placeEspressoHouseAtPosition(int i, int j) {
        graph[i][j] = 'E';
        makeAdjacent(i, j + 1);
        makeAdjacent(i, j - 1);
        makeAdjacent(i + 1, j);
        makeAdjacent(i - 1, j);
    }

    private void makeAdjacent(int i, int j) {
        if (isValidPosition(i, j)) graph[i][j] = ADJACENT;
    }

    private boolean isPositionBelowValid(int i, int j) {
        return isValidPosition(i + 1, j);
    }

    private boolean isPositionRightValid(int i, int j) {
        return isValidPosition(i, j + 1);
    }

    private boolean isPositionLeftValid(int i, int j) {
        return isValidPosition(i, j - 1);
    }

    private boolean isValidPosition(int i, int j) {
        try {
            return graph[i][j] == FREESPACE;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private void show() {
        for (int i = 0; i < graph.length; i++) {

            for (char c : graph[i]) {
                if (c != ADJACENT)
                    System.out.print(c);
                else System.out.print(".");
            }

            if (i < graph.length - 1)
                System.out.println();
        }
    }
}
