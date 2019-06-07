import java.util.Scanner;

public class TurtleMaster {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] graph = new char[8][8];
        for (int i = 0; i < 8; i++) {
            graph[i] = sc.nextLine().toCharArray();
        }
        Game game = new Game(graph);
        game.startGame(sc.nextLine());
    }
}

class Game {
    private char[][] graph;
    private int verticalDir, horizontalDir;
    private int posI, posJ, futPosI, futPosJ;
    private char moveForward = 'F', turnRight = 'R', turnLeft = 'L', fireLaser = 'X', iceBlock = 'I', freeBlock = '.', diamond = 'D';
    boolean isOnDiamond;

    public Game(char[][] graph) {
        this.graph = graph;
        posI = graph.length - 1;
        posJ = 0;
        verticalDir = 0;
        horizontalDir = 1;
        updateFuturePosition();
    }

    public void startGame(String commands) {
        graph[posI][posJ] = 'T';
//        show();
        for (int i = 0; i < commands.length(); i++) {
            char command = commands.charAt(i);
            if (command == moveForward) {
                if (!move()) {
                    bug();
                    return;
                }
            } else if (command == fireLaser) {
                if (!fireLaser()) {
                    bug();
                    return;
                }
            } else turn(command);
//            show();
        }

        if (isOnDiamond) System.out.println("Diamond!");
        else bug();
    }

//    private void show() {
//        String ANSI_RESET = "\u001B[0m";
//        String ANSI_RED = "\u001B[31m";
//        String ANSI_GREEN = "\u001B[32m";
//
//        for (int i = 0; i < graph.length; i++) {
//            System.out.println();
//            for (int j = 0; j < graph[i].length; j++) {
//                if (i == futPosI && j == futPosJ) {
//                    System.out.print(ANSI_RED + graph[i][j] + ANSI_RESET);
//                } else if (i == posI && j == posJ) {
//                    System.out.print(ANSI_GREEN + "T" + ANSI_RESET);
//                } else
//                    System.out.print(graph[i][j]);
//            }
//        }
//        System.out.println();
//    }

    private void turn(char c) {
        if (c == turnRight) {
            turnRight();
        } else {
            turnLeft();
        }
        updateFuturePosition();
    }

    private void turnRight() {
        if (verticalDir == 0 && horizontalDir == 1) {
            verticalDir = 1;
            horizontalDir = 0;
        } else if (verticalDir == 1 && horizontalDir == 0) {
            verticalDir = 0;
            horizontalDir = -1;
        } else if (verticalDir == 0 && horizontalDir == -1) {
            verticalDir = -1;
            horizontalDir = 0;
        } else {
            verticalDir = 0;
            horizontalDir = 1;
        }
    }

    private void turnLeft() {
        if (verticalDir == 0 && horizontalDir == 1) {
            verticalDir = -1;
            horizontalDir = 0;
        } else if (verticalDir == -1 && horizontalDir == 0) {
            verticalDir = 0;
            horizontalDir = -1;
        } else if (verticalDir == 0 && horizontalDir == -1) {
            verticalDir = 1;
            horizontalDir = 0;
        } else {
            verticalDir = 0;
            horizontalDir = 1;
        }
    }

    private boolean fireLaser() {
        if (isValidLaserPosition()) {
            graph[futPosI][futPosJ] = freeBlock;
            return true;
        }
        return false;

    }


    private boolean move() {
        if (isValidPosition(futPosI, futPosJ)) {
            graph[posI][posJ] = freeBlock;
            posI = futPosI;
            posJ = futPosJ;
            updateFuturePosition();

            isOnDiamond = (graph[posI][posJ] == diamond);

            return true;

        } else return false;
    }

    private boolean isValidPosition(int i, int j) {
        try {
            char block = graph[i][j];
            return (block == freeBlock || block == diamond);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private boolean isValidLaserPosition() {
        try {
            return (graph[futPosI][futPosJ] == iceBlock);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private void updateFuturePosition() {
        futPosI = posI + verticalDir;
        futPosJ = posJ + horizontalDir;
    }

    private boolean bug() {
        System.out.println("Bug!");
        return false;
    }
}
