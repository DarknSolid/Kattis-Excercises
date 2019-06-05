import java.util.Scanner;

public class V2048 {

    private static int direction;
    private static final int LEFT = 0, RIGHT = 2, UP = 1, DOWN = 3;
    private static int startIndex, endIndex, incrementer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] board = fillBoard(sc);
        direction = sc.nextInt();
        setLoopDirection(board.length);
        nextMove(board);
        show(board);

    }

    private static void show(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.println();
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
                if (j < board[i].length) System.out.print(" ");
            }
        }
    }

    private static void nextMove(int[][] board) {

        for (int i = 0; i < board.length; i++) {
            int lookingFor = -1;
            int lookingForIndexI = -1, lookingForIndexJ = -1;
            int current;

            for (int j = startIndex; shouldKeepGoing(j, board.length); j += incrementer) {
                int firstArray = firstArray(i, j);
                int secondArray = secondArray(i, j);
                current = board[firstArray][secondArray];

                if (current == 0) continue;

                if (lookingFor == -1 || current != lookingFor) {
                    lookingFor = current;
                    lookingForIndexI = firstArray;
                    lookingForIndexJ = secondArray;
                } else if (lookingFor == current) {
                    board[lookingForIndexI][lookingForIndexJ] += current;
                    board[firstArray][secondArray] = 0;
                    lookingFor = -1;
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            int lastZeroIndexI = -1, lastZeroIndexJ = -1;
            boolean hasActiveZero = false;
            for (int j = startIndex; shouldKeepGoing(j, board.length); j += incrementer) {
                int firstArray = firstArray(i, j);
                int secondArray = secondArray(i, j);
                int current = board[firstArray][secondArray];

                if (current == 0 && !hasActiveZero) {
                    lastZeroIndexI = firstArray;
                    lastZeroIndexJ = secondArray;
                    hasActiveZero = true;
                }

                if (current != 0 && hasActiveZero) {
                    board[lastZeroIndexI][lastZeroIndexJ] = current;
                    board[firstArray][secondArray] = 0;
                    lastZeroIndexI = firstArray;
                    lastZeroIndexJ = secondArray;
                    hasActiveZero = true;
                }
            }
        }

    }

    private static int secondArray(int i, int j) {
        if (direction == LEFT || direction == RIGHT) return j;
        return i;
    }

    private static int firstArray(int i, int j) {
        if (direction == LEFT || direction == RIGHT) return i;
        return j;
    }

    private static boolean shouldKeepGoing(int j, int size) {
        if (startIndex == 0 && j < endIndex) return true;
        return (startIndex == size - 1 && j >= 0);
    }

    private static void setLoopDirection(int size) {
        if (direction == LEFT || direction == UP) {
            startIndex = 0;
            endIndex = size;
            incrementer = 1;
        }
        if (direction == RIGHT || direction == DOWN) {
            startIndex = size - 1;
            endIndex = 0;
            incrementer = -1;
        }
    }


    private static int[][] fillBoard(Scanner sc) {
        int[][] board = new int[4][4];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = sc.nextInt();
            }
        }
        return board;
    }
}
