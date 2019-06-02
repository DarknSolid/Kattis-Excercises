import java.util.ArrayList;
import java.util.Scanner;

public class TenKindsOfPeopleV2 {

    private static ArrayList<String> graph;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int height = sc.nextInt();
        int width = sc.nextInt();
        sc.nextLine();
        graph = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            graph.add(sc.nextLine());
        }

        WeightedQuickUnion wqu = new WeightedQuickUnion(height * width);

        //Union the graph:
        long start = System.currentTimeMillis();
        for (int i = 0; i < height; i++) {

            for (int j = 0; j < width; j++) {

                int current = singletonValueOf(i, j);
                int neighbour;
                int binaryCurrent = binaryValueOf(i, j);
                int binaryNeighbour;


                if (j < width - 1) {
                    neighbour = singletonValueOf(i, j + 1);
                    binaryNeighbour = binaryValueOf(i, j + 1);
                    if (binaryCurrent == binaryNeighbour)
                        wqu.union(current, neighbour);
                }
                if (i < height - 1) {
                    neighbour = singletonValueOf(i + 1, j);
                    binaryNeighbour = binaryValueOf(i + 1, j);
                    if (binaryCurrent == binaryNeighbour)
                        wqu.union(current, neighbour);
                }
            }
        }
        System.out.println("Union time: " + ((System.currentTimeMillis() - start)/1000f));

        int queries = sc.nextInt();
        for (int i = 0; i < queries; i++) {
            int x1 = sc.nextInt() - 1;
            int y1 = sc.nextInt() - 1;
            int x2 = sc.nextInt() - 1;
            int y2 = sc.nextInt() - 1;
            int p = singletonValueOf(x1, y1);
            int q = singletonValueOf(x2, y2);
//            System.out.println(p + ", " + q);

            int binaryP = binaryValueOf(x1, y1);
            int binaryQ = binaryValueOf(x2, y2);
//            System.out.println(binaryP + ", " + binaryQ);

            if (binaryP == binaryQ) {

                if (wqu.isInSameSet(p, q)) {
                    if (binaryP == 1) {
                        System.out.println("decimal");
                        continue;
                    } else {
                        System.out.println("binary");
                        continue;
                    }
                }
            }
            System.out.println("neither");
        }
    }
        private static int binaryValueOf ( int i, int j){
            return Character.getNumericValue(graph.get(i).charAt(j));
        }

        private static int singletonValueOf ( int i, int j){
            return (graph.get(i).length() * i + j);
        }
    }

    class WeightedQuickUnion {
        int[] parents, sz;

        public WeightedQuickUnion(int singletons) {
            this.parents = new int[singletons];
            this.sz = new int[singletons];

            for (int i = 0; i < parents.length; i++) {
                parents[i] = i;
            }
        }

        public void union(int p, int q) {
            if (isInSameSet(p, q)) return;
            int pParent = parentOf(p);
            int qParent = parentOf(q);

            if (sz[pParent] >= sz[qParent]) {
                parents[qParent] = pParent;
                sz[pParent] += sz[qParent];
            } else {
                parents[pParent] = qParent;
                sz[qParent] += sz[pParent];
            }
        }

        public boolean isInSameSet(int p, int q) {
            return parentOf(p) == parentOf(q);
        }

        private int parentOf(int p) {
            int current = p;
            int parent = parents[p];
            while (current != parent) {
                current = parent;
                parent = parents[current];
            }
            return parent;
        }

        public void show() {
            for (int i = 0; i < parents.length; i++) {
                System.out.println(i + ": " + parents[i]);
            }
        }
    }
