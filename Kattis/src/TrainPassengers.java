import java.util.Scanner;

public class TrainPassengers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int capacity = sc.nextInt();
        int stops = sc.nextInt();
        int lastStop = stops - 1;

        Train train = new Train(capacity);

        for (int i = 0; i < stops; i++) {
            int peopleUnboarding = sc.nextInt();
            int peopleBoarding = sc.nextInt();
            int peopleWaiting = sc.nextInt();

            if (!train.unboardTrain(peopleUnboarding)) {
                impossible();
                return;
            }
            if (!train.boardTrain(peopleBoarding)) {
                impossible();
                return;
            }
            if (peopleWaiting > 0 && !train.isFull()) {
                impossible();
                return;
            }
            if (i == lastStop) {
                if (!train.isEmpty() || peopleWaiting > 0) {
                    impossible();
                    return;
                }
            }
        }
        System.out.println("possible");
    }

    private static void impossible() {
        System.out.println("impossible");
    }
}

class Train {
    int capacity;
    int passengers;

    public Train(int capacity) {
        this.capacity = capacity;
        passengers = 0;
    }

    public boolean boardTrain(int amount) {
        int newAmount = passengers + amount;
        if (newAmount <= capacity) {
            passengers = newAmount;
            return true;
        }
        return false;
    }

    public boolean unboardTrain(int amount) {
        int newAmount = passengers - amount;
        if (newAmount >= 0) {
            passengers = newAmount;
            return true;
        }
        return false;
    }

    public boolean isFull() {
        return passengers == capacity;
    }

    public boolean isEmpty() {
        return passengers == 0;
    }
}
